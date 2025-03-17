package com.chatbot.chatgptbot.config;


import com.chatbot.chatgptbot.bot.TelegramBot;
import com.chatbot.chatgptbot.openai.OpenAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfiguration {

    @Bean
    public TelegramBot telegramBot(@Value("${bot.token}") String token,
                                   TelegramBotsApi telegramBotsApi,
                                   OpenAiClient openAiClient) {
        var botOptions = new DefaultBotOptions();
        var bot = new TelegramBot(botOptions, token, openAiClient);
        try {
            telegramBotsApi().registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return bot;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        try {
            return new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
