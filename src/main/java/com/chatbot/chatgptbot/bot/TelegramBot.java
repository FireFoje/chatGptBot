package com.chatbot.chatgptbot.bot;

import com.chatbot.chatgptbot.openai.OpenAiClient;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    private final OpenAiClient openAiClient;

    public TelegramBot(DefaultBotOptions options, String botToken, OpenAiClient openAiClient) {
        super(options, botToken);
        this.openAiClient = openAiClient;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            var chatCompletionResponse = openAiClient.createChatCompletionObject(message);
            var textResponse = chatCompletionResponse.choices().get(0).message().content();
            SendMessage sendMessage = new SendMessage(chatId.toString(), textResponse);
            try {
                sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "for_ourselves_bot";
    }
}
