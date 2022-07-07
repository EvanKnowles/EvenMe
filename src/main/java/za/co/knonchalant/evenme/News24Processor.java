package za.co.knonchalant.evenme;

import org.neo4j.driver.types.Node;
import org.wikidata.wdtk.datamodel.interfaces.*;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.io.IOException;
import java.util.*;

public class News24Processor {
    private static final Map<String, EntityDocument> CACHED_WIKI_DOCS = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException, MediaWikiApiErrorException {

        List<Node> unprocessedArticles = Neo.find("Article", Collections.singletonMap("processed", "false"));
        if (unprocessedArticles.isEmpty()) {
            System.out.println("Finding more articles...");
            Map<String, Article> articles = News24.getArticles();
            for (Map.Entry<String, Article> articleEntry : articles.entrySet()) {
                String url = articleEntry.getKey();
                List<Node> nodes = Neo.find("Article", Collections.singletonMap("url", url));
                if (nodes.isEmpty()) {
                    Neo.create("Article", Map.of("name", articleEntry.getValue().getName(),
                            "url", url,
                            "article", escape(articleEntry.getValue().getArticle()),
                            "processed", "false"));
                }
            }

            unprocessedArticles = Neo.find("Article", Collections.singletonMap("processed", "false"));
        }

        NLP nlp = new NLP();
        HashSet<String> seen = new HashSet<>();

        for (Node article : unprocessedArticles) {
            processArticle(nlp, seen, article);
            Node update = Neo.update(article, Collections.singletonMap("processed", "true"));
            System.out.println(update.get("processed"));
        }


    }

    private static Object escape(String value) {
        return value.replaceAll("\"", "\\\"\"");
    }

    private static void processArticle(NLP nlp, HashSet<String> seen, Node articleNode) throws MediaWikiApiErrorException, IOException {
        String article = articleNode.get("article").asString();
        HashMap<String, Set<Set<String>>> ner = nlp.ner(article);
        for (Map.Entry<String, Set<Set<String>>> namedEntities : ner.entrySet()) {
            System.out.println("For " + namedEntities.getKey());
            System.out.println(namedEntities.getValue());

            if (namedEntities.getKey().equals("PERSON")) {
                WikibaseDataFetcher wbdf = WikibaseDataFetcher.getWikidataDataFetcher();
                for (Set<String> strings : namedEntities.getValue()) {
                    String name = "";
                    for (String potentialName : strings) {
                        if (potentialName.length() > name.length()) {
                            name = potentialName;
                        }
                    }

                    Map<String, String> nameMap = Collections.singletonMap("name", name);
                    List<Node> peopleOptions = Neo.find("Entity", nameMap);
                    Node thePerson;
                    if (peopleOptions.isEmpty()) {
                        thePerson = Neo.create("Entity", Collections.singletonMap("name", name));
                    } else {
                        thePerson = peopleOptions.get(0);
                    }
                    Neo.link(thePerson, articleNode, "APPEARS_IN");

                    if (seen.contains(name)) {
                        System.out.println("Seen " + name + " skipping...");
                        continue;
                    }

                    seen.add(name);

                    EntityDocument namedWikiEntity = wbdf.getEntityDocumentByTitle("enwiki",
                            name);

                    if (namedWikiEntity == null) {
                        System.out.println("Wiki doesn't know: " + name);
                    } else {
                        List<StatementGroup> statementGroups = ((ItemDocument) namedWikiEntity).getStatementGroups();
                        List<String> statementIds = new ArrayList<>();
                        Map<String, String> propertyToData = new HashMap<>();

                        for (StatementGroup statementGroup : statementGroups) {
                            for (Statement statement : statementGroup.getStatements()) {
                                Snak mainSnak = statement.getMainSnak();
                                String propertyId = mainSnak.getPropertyId().getId();

                                if (statement.getMainSnak() instanceof ValueSnak) {
                                    ValueSnak valueSnak = (ValueSnak) statement.getMainSnak();
                                    if (valueSnak.getValue() instanceof ItemIdValue) {
                                        String dataId = ((ItemIdValue) valueSnak.getValue()).getId();
                                        statementIds.add(propertyId);
                                        statementIds.add(dataId);
                                        propertyToData.put(propertyId, dataId);
                                    } else {
                                        System.out.println(valueSnak);
                                    }
                                }
                            }

                        }

                        Map<String, EntityDocument> entityDocuments = getEntityDocuments(wbdf, statementIds);
                        for (Map.Entry<String, String> propertyToDataEntry : propertyToData.entrySet()) {
                            LabeledDocument propertyDocument = (PropertyDocument) entityDocuments.get(propertyToDataEntry.getKey());
                            LabeledDocument itemDocument = (ItemDocument) entityDocuments.get(propertyToDataEntry.getValue());

                            if (itemDocument == null || propertyDocument == null) {
                                continue;
                            }

                            WikiProps prop = WikiProps.get(propertyDocument.getEntityId().getId());
                            String propLabel = propertyDocument.getLabels().get("en").getText();
                            String finalProp = prop != null ? prop.toString() : propLabel;
                            finalProp = finalProp.replaceAll(" ", "_").replaceAll("[^A-Za-z_]", "").toUpperCase(Locale.ROOT);

                            MonolingualTextValue englishValue = itemDocument.getLabels().get("en");
                            if (englishValue != null) {
                                String otherEnd = englishValue.getText();
                                List<Node> potentialNames = Neo.find("Entity", Collections.singletonMap("name", otherEnd));
                                Node theItem;
                                if (potentialNames.isEmpty()) {
                                    theItem = Neo.create("Entity", Map.of("name", otherEnd, "entity_id", itemDocument.getEntityId().getId()));
                                } else {
                                    theItem = potentialNames.get(0);
                                }

                                Neo.link(thePerson, theItem, finalProp);
                            }
                        }

                    }
                }

            }


        }
    }

    private static Map<String, EntityDocument> getEntityDocuments(WikibaseDataFetcher wbdf, List<String> statementIds) throws MediaWikiApiErrorException, IOException {
        HashMap<String, EntityDocument> stringEntityDocumentHashMap = new HashMap<>();
        List<String> lookUpstatementIds = new ArrayList<>();
        for (String statementId : statementIds) {
            if (CACHED_WIKI_DOCS.containsKey(statementId)) {
                stringEntityDocumentHashMap.put(statementId, CACHED_WIKI_DOCS.get(statementId));
                System.out.println("Cache hit on " + statementId);
            } else {
                lookUpstatementIds.add(statementId);
            }
        }

        Map<String, EntityDocument> entityDocuments = wbdf.getEntityDocuments(lookUpstatementIds);
        CACHED_WIKI_DOCS.putAll(entityDocuments);
        stringEntityDocumentHashMap.putAll(entityDocuments);
        return stringEntityDocumentHashMap;
    }



}
