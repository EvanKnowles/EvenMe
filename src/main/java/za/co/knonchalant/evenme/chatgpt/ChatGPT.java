package za.co.knonchalant.evenme.chatgpt;

import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.News24;
import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTResponse;
import za.co.knonchalant.evenme.client.REST;
import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTRequest;
import za.co.knonchalant.evenme.chatgpt.domain.Message;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatGPT {
    public static final String KEY = "CHATKEY";
    public static final String CONVERT_TO_FACTS = "First, identify all the entities in the following article, then convert the article into basic facts of the form (entity)-[VERB]->(fact). As an example (EFF)-[IMPLICATES]->(President of South Africa): ";
    public static final String EXTRACT_PEOPLE_PLACES = "Extract people and places from the following article in the form (person)-[IS_THE]->(description), as an example (Cyril Ramaphosa)-[IS_THE]->(President of South Africa) ";
    public static final String EXTRACT_LINKS = "Extract links between entities in the following article in the form (entity)-[FACT]->(other entity), as an example (Cyril Ramaphosa)-[WORKED_WITH]->(Julius Malema) ";

    public static void main(String[] args) throws IOException {


        Map<String, Article> articles = News24.getArticles();

        for (Map.Entry<String, Article> stringArticleEntry : articles.entrySet()) {
            Article value = stringArticleEntry.getValue();
            System.out.println(value.getUrl());
            String article = value.getArticle();
            ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
            chatGPTRequest.setTemperature(0.2);
            chatGPTRequest.setModel("gpt-4o");
            Message message = new Message();
            message.setRole("user");
            message.setContent(EXTRACT_LINKS + article);
            chatGPTRequest.setMessages(List.of(message));

            ChatGPTResponse s = REST.url("https://api.openai.com/v1/chat/completions")
                    .body(chatGPTRequest)
                    .authorization(KEY)
                    .post(ChatGPTResponse.class);

            System.out.println(s.getChoices().get(0).getMessage());
        }
    }

    public static ChatGPTResponse submit(String prompt) throws IOException {
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setTemperature(0.2);
        chatGPTRequest.setModel("gpt-3.5-turbo");
        Message message = new Message();
        message.setRole("user");
        message.setContent(prompt);
        chatGPTRequest.setMessages(List.of(message));
        return REST.url("https://api.openai.com/v1/chat/completions")
                .body(chatGPTRequest)
                .authorization(KEY)
                .post(ChatGPTResponse.class);
    }
}
