package com.chatbot.chatgptbot.openai;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class OpenAiClient {

    private final String token;

    private final RestTemplate restTemplate;

    public OpenAiClient(String token, RestTemplate restTemplate) {
        this.token = token;
        this.restTemplate = restTemplate;
    }

    public ChatCompletionObject createChatCompletionObject(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        httpHeaders.set("Content-Type", "application/json");

        String request = """
                {
                    "model": "gpt-4o-mini",
                    "messages": [
                      {
                        "role": "system",
                        "content": "You are a helpful assistant."
                      },
                      {
                        "role": "user",
                        "content": "%s"
                      }
                    ]
                  }""".formatted(message);

        HttpEntity<String> http = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<ChatCompletionObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, http, ChatCompletionObject.class);

        return responseEntity.getBody();
    }
}
