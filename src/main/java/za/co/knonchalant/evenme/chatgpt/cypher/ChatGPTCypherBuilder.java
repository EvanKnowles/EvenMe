package za.co.knonchalant.evenme.chatgpt.cypher;

import za.co.knonchalant.evenme.cache.CachePopulator;
import za.co.knonchalant.evenme.chatgpt.ChatGPT;
import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTResponse;

import java.io.IOException;

public class ChatGPTCypherBuilder implements CachePopulator {
    public static final String PROMPT =
            "Create Cypher syntax for all facts from the following news article. Only output the Cypher result, nothing else.\n" +
                    "Create statements that do not fail on create if the data already exists. Create links between nodes. \n" +
                    "Use the following terms: Event, Individual, Outcome, Company, Party, Organization.\n" +
                    "Include descriptions as attributes on nodes.\n" +
                    "Include dates where available as attributes on nodes.";

    @Override
    public String populate(String articleContent) throws IOException {
        ChatGPTResponse submit = ChatGPT.submit(PROMPT + "\n" + articleContent);
        return submit.getChoices().get(0).getMessage().getContent();
    }
}