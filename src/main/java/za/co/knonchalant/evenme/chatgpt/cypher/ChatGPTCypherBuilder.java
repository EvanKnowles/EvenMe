package za.co.knonchalant.evenme.chatgpt.cypher;

import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.cache.CachePopulator;
import za.co.knonchalant.evenme.chatgpt.ChatGPT;
import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTResponse;

import java.io.IOException;

public class ChatGPTCypherBuilder implements CachePopulator {
    public static final String PROMPT =
            "First judge if this is an article about political events or politicians. If it is not, then only return a blank line.\n" +
                    "Otherwise: \n" +
                    "* Create Cypher syntax for all facts from the following news article. Only output the Cypher result, nothing else.\n" +
                    "* Use MERGE with partial matches, ON CREATE and ON MATCH to ensure that all inserts are idempotent. \n" +
                    "* Create links between nodes. \n" +
                    "* Use the following terms: Event, Individual, Outcome, Company, Party, Organization.\n" +
                    "* Store descriptions as attributes on nodes.\n" +
                    "* Include dates where available as attributes on nodes.";

    private final String key;

    public ChatGPTCypherBuilder(Environment environment)
    {
        this.key = environment.chatGPTKey;
    }

    @Override
    public String populate(String articleContent) throws IOException {
        ChatGPTResponse submit = ChatGPT.submit(PROMPT + "\n" + articleContent, key);
        return submit.getChoices().get(0).getMessage().getContent();
    }
}