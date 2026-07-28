package com.learning.spring.ai.demo_project.demo_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MessageChatMemoryAdvisorServiceTest {

    @Autowired
    private MessageChatMemoryAdvisorService service;

    @Test
    void shouldRememberPreviousConversation() {
        String conversationId = "aman-session";
        ask(conversationId, "My name is Aman");
        ask(conversationId, "I am a Java Backend Developer");
        ask(conversationId, "I am learning Spring AI");
        ask(conversationId, "What is my name?");
        ask(conversationId, "What technology am I learning?");
    }


    private void ask(String conversationId, String question) {
        String response = service.chat(conversationId, question);
        System.out.println("--------------------------------");
        System.out.println("USER : " + question);
        System.out.println("AI   : " + response);
    }

}
