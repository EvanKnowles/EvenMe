package za.co.knonchalant.evenme;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.wikidata.wdtk.datamodel.interfaces.*;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.io.IOException;
import java.util.*;

public class News24 {
    public static final String BASE = "https://www.news24.com";
    private static final Map<String, EntityDocument> CACHED_WIKI_DOCS = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException, MediaWikiApiErrorException {

        List<Node> unprocessedArticles = Neo.find("Article", Collections.singletonMap("processed", "false"));
        if (unprocessedArticles.isEmpty()) {
            Map<String, Article> articles = getArticles();
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


    public static Map<String, Article> getArticles() throws IOException {
        String result = REST.sendGet(BASE + "/api/article/loadmore/tag?tagType=topics&tag=politics&pageNumber=1&pageSize=200&breadcrumb=news24/Tags/Topics/politics&isMobile=false&thumbnailItemType=_SmallThumbItem", Collections.emptyMap(), Collections.emptyMap());
        Gson gson = new Gson();
        News24Response news24Response = gson.fromJson(result, News24Response.class);

        Document parse = Jsoup.parse(news24Response.getHtmlContent());

        Map<String, Article> resultArray = new HashMap<>();
        Elements a = parse.select("a");
        for (Element element : a) {
            String url = element.attr("href");
            if (!url.startsWith("http")) {
                url = BASE + url;
            }
            StringBuilder resultString = getArticleForURL(url);
            resultArray.put(url, new Article(element.attr("aria-label"), url, resultString.toString()));

        }

        return resultArray;
    }

    public static StringBuilder getArticleForURL(String url) throws IOException {
        Document document = Jsoup.connect(url).cookie("24uat", "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFhZWY1NjlmNTI0MTRlOWY0YTcxMDRiNmQwNzFmMDY2ZGZlZWQ2NzciLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRXZhbiBKYW1lcyBLbm93bGVzIiwicGljdHVyZSI6Imh0dHBzOi8vZ3JhcGguZmFjZWJvb2suY29tLzg1NjE0MDExMS9waWN0dXJlIiwidGZfdHJhZmZpY19hbGwiOnRydWUsInRmX25ld3NsZXR0ZXJzX2ZyZWUiOnRydWUsInRmX3dlYXRoZXJfYWxsIjp0cnVlLCJ0Zl9ib29rbWFya3MiOnRydWUsInRmX3JlZ2lzdGVyZWQiOnRydWUsInRmX2NvbW1lbnRzIjp0cnVlLCJ0Zl9hcnRpY2xlX2F1ZGlvIjp0cnVlLCJ0Zl9wZGYiOnRydWUsInRmX2FydGljbGVzIjp0cnVlLCJ0Zl9uZXdzbGV0dGVyc19hbGwiOnRydWUsInRmX3N1YnNjcmliZWQiOnRydWUsInRmX3VzZXJUeXBlIjozLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHdlbnR5Zm91ci1lbmdsaXNoLWxpdmUiLCJhdWQiOiJ0d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1dGhfdGltZSI6MTY1MzI4NjUwOSwidXNlcl9pZCI6IlhKc3NDSHl5SHVYMDFBZURaY1NvTlU5MVdVMDMiLCJzdWIiOiJYSnNzQ0h5eUh1WDAxQWVEWmNTb05VOTFXVTAzIiwiaWF0IjoxNjU1MTUyNzg3LCJleHAiOjE2NTUxNTYzODcsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImZhY2Vib29rLmNvbSI6WyI4NTYxNDAxMTEiXSwiZW1haWwiOlsicGlyYXRlYW5nZWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZmFjZWJvb2suY29tIn19.J74so34417M_xiJJqhXew4oeJafWv3ZUwE93qLWysie_inJRAmqM4oN8zSatc4y-A4Vv0AlzyZ4ZvBBZHFlEN6HE2VQ1ncYY8ynhso75uRFkd21uznHM2Mc9Nb56wWsT0GpHOe4p1swmbjwxWMFEK0P9tRoa9TRAqh58-lhS5FYamghWTCrbCPA4sg3GVzbVAqv8Eh04YnDupX3w-yNy9Cx-yeyrXKBgJFy_1nskQ7WcS28IMHznn9sIYQlAR-q3q2sKdO0IL0dUrZKrkHJ_JHHCag44teNKnO9eVlLN-c5zP1hBIjayiUS8EEoeN4LgNmZLCZAsrF-TCfconBES3g")
                .get();
        Elements select = document.select(".article__body p");
        StringBuilder resultString = new StringBuilder();
        for (Element element1 : select) {
            resultString.append(element1.text()).append(" ");
        }
        return resultString;
    }

}
