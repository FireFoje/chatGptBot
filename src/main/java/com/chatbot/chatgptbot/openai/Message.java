package com.chatbot.chatgptbot.openai;


import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(@JsonProperty("role") String role, @JsonProperty("content") String content) {

}
