package za.co.knonchalant.evenme;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wikidata.wdtk.datamodel.interfaces.*;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.io.IOException;
import java.util.*;

public class News24 {
    public static final String BASE = "https://www.news24.com";

    public static void main(String[] args) throws IOException, ClassNotFoundException, MediaWikiApiErrorException {
        List<String> articles = Collections.singletonList(getArticleForURL("https://www.news24.com/news24/southafrica/news/dada-morero-beats-eunice-mgcina-to-top-anc-spot-in-joburg-regional-conference-20220605").toString());

        NLP nlp = new NLP();
        for (String article : articles) {
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

                            Map<String, EntityDocument> entityDocuments = getEntityDocuments(wbdf, statementIds);
                            for (Map.Entry<String, String> propertyToDataEntry : propertyToData.entrySet()) {
                                LabeledDocument propertyDocument = (PropertyDocument) entityDocuments.get(propertyToDataEntry.getKey());
                                LabeledDocument itemDocument = (ItemDocument) entityDocuments.get(propertyToDataEntry.getValue());
                                System.out.println(propertyDocument.getLabels().get("en").getText());
                                System.out.println(itemDocument.getLabels().get("en").getText());
                            }

                        }
                    }

                }


            }

        }


    }

    private static Map<String, EntityDocument> getEntityDocuments(WikibaseDataFetcher wbdf, List<String> statementIds) throws MediaWikiApiErrorException, IOException {
        return wbdf.getEntityDocuments(statementIds);
    }


    public static List<String> getArticles() throws IOException {
        String result = REST.sendGet(BASE + "/api/article/loadmore/tag?tagType=topics&tag=politics&pageNumber=4&pageSize=19&breadcrumb=news24/Tags/Topics/politics&isMobile=false&thumbnailItemType=_SmallThumbItem", Collections.emptyMap(), Collections.emptyMap());
        Gson gson = new Gson();
        News24Response news24Response = gson.fromJson(result, News24Response.class);

        Document parse = Jsoup.parse(news24Response.getHtmlContent());

        ArrayList<String> resultArray = new ArrayList<String>();

        Elements a = parse.select("a");
        for (Element element : a) {
            String url = element.attr("href");
            if (!url.startsWith("http")) {
                url = BASE + url;
            }
            StringBuilder resultString = getArticleForURL(url);
            resultArray.add(resultString.toString());

        }

        return resultArray;
    }

    public static StringBuilder getArticleForURL(String url) throws IOException {
        Document document = Jsoup.connect(url).cookie("24uat", "eyJhbGciOiJSUzI1NiIsImtpZCI6ImY0ZTc2NDk3ZGE3Y2ZhOWNjMDkwZDcwZTIyNDQ2YTc0YjVjNTBhYTkiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRXZhbiBKYW1lcyBLbm93bGVzIiwicGljdHVyZSI6Imh0dHBzOi8vZ3JhcGguZmFjZWJvb2suY29tLzg1NjE0MDExMS9waWN0dXJlIiwidGZfdHJhZmZpY19hbGwiOnRydWUsInRmX25ld3NsZXR0ZXJzX2ZyZWUiOnRydWUsInRmX3dlYXRoZXJfYWxsIjp0cnVlLCJ0Zl9ib29rbWFya3MiOnRydWUsInRmX3JlZ2lzdGVyZWQiOnRydWUsInRmX2NvbW1lbnRzIjp0cnVlLCJ0Zl9hcnRpY2xlX2F1ZGlvIjp0cnVlLCJ0Zl9wZGYiOnRydWUsInRmX2FydGljbGVzIjp0cnVlLCJ0Zl9uZXdzbGV0dGVyc19hbGwiOnRydWUsInRmX3N1YnNjcmliZWQiOnRydWUsInRmX3VzZXJUeXBlIjozLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHdlbnR5Zm91ci1lbmdsaXNoLWxpdmUiLCJhdWQiOiJ0d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1dGhfdGltZSI6MTY1MzI4NjUwOSwidXNlcl9pZCI6IlhKc3NDSHl5SHVYMDFBZURaY1NvTlU5MVdVMDMiLCJzdWIiOiJYSnNzQ0h5eUh1WDAxQWVEWmNTb05VOTFXVTAzIiwiaWF0IjoxNjU0NDQ2NjI0LCJleHAiOjE2NTQ0NTAyMjQsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImZhY2Vib29rLmNvbSI6WyI4NTYxNDAxMTEiXSwiZW1haWwiOlsicGlyYXRlYW5nZWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZmFjZWJvb2suY29tIn19.dXK2xKkRmhpbUKrcoa0JObuWKPeIC-V-SqKSVfoSMQdqloKh5c-IJYtzxDdQBGBrj0Dgb67t69IyfzO85At201KxGTsHuHfoP2g7UubseYptjn4M2VUixD3xSzl-thPueVpe6QViweKvzTbYMAVa4UPSLaa4qf57hQ6gdS689Tib7-90otL009E-XzF5fXZ_3mjJvX3HQrMA9xog4KmVMoyRxnznasszM8jS0Wpa4sJVi5inHTaTJvZGOYILi0p2jFBdZQ6LT1DN8hd3TYiWgXvydgJbJ2LokMYrjbvRdpPXpfqmI2Gw1rwDoM-eEibzaohtZFdK6Rkcd_R-HSlt2A; 24cat=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2NTQ0NTM4MjcsIm5iZiI6MTY1NDQ0NjYyNywiZXhwIjoxNjU0NDUwMjI0LCJ1c2VyIjp7ImlkIjoiWEpzc0NIeXlIdVgwMUFlRFpjU29OVTkxV1UwMyIsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwidXNlcm5hbWUiOiJwaXJhdGVhbmdlbCJ9fQ.eZqcJzLDTCPiQM4dWBlCxuhxDq-Dyn-QViWgD8whLqc")
                .get();
        Elements select = document.select(".article__body p");
        StringBuilder resultString = new StringBuilder();
        for (Element element1 : select) {
            resultString.append(element1.text()).append(" ");
        }
        return resultString;
    }

}
