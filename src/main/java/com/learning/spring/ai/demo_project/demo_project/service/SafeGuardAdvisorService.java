package com.learning.spring.ai.demo_project.demo_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SafeGuardAdvisorService {

    private final ChatClient chatClient;
    private final SafeGuardAdvisor safeGuardAdvisor;

    public String ask(String question) {
        return chatClient
                .prompt()
                .user(question)
                .advisors(safeGuardAdvisor)
                .call()
                .content();
    }
}
