package za.co.knonchalant.evenme.chatgpt;

import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTRequest;
import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTResponse;
import za.co.knonchalant.evenme.chatgpt.domain.Message;
import za.co.knonchalant.evenme.client.REST;

import java.io.IOException;
import java.util.List;

public class ChatGPT {
    public static ChatGPTResponse submit(String prompt, String key) throws IOException {
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setTemperature(0.2);
        chatGPTRequest.setModel("gpt-4o");
        Message message = new Message();
        message.setRole("user");
        message.setContent(prompt.replaceAll("\\P{ASCII}", ""));
        chatGPTRequest.setMessages(List.of(message));
        return REST.url("https://api.openai.com/v1/chat/completions")
                .body(chatGPTRequest)
                .authorization(key)
                .post(ChatGPTResponse.class);
    }
}
