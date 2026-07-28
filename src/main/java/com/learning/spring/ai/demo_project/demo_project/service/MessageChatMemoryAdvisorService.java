package com.learning.spring.ai.demo_project.demo_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageChatMemoryAdvisorService {

    private final ChatClient chatClient;
    private final MessageChatMemoryAdvisor memoryAdvisor;

    public String chat(String conversationId, String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .advisors(memoryAdvisor)
                .advisors(advisor ->
                        advisor.param(
                                ChatMemory.CONVERSATION_ID,
                                conversationId
                        )
                )
                .call()
                .content();
    }

}