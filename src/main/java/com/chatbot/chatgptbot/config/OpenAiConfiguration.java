package com.chatbot.chatgptbot.config;

import com.chatbot.chatgptbot.openai.OpenAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAiConfiguration {

    @Bean
    public OpenAiClient openAiClient(@Value("${openai.token}") String openaiToken,
                                     RestTemplateBuilder restTemplateBuilder) {

        return new OpenAiClient(openaiToken, restTemplateBuilder.build());
    }
}
