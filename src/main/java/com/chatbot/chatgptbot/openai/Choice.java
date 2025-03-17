package com.chatbot.chatgptbot.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(@JsonProperty("message") Message message) {

}
