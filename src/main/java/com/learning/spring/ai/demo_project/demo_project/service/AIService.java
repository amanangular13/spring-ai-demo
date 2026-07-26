package com.learning.spring.ai.demo_project.demo_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient;

    public String getJoke(String topic) {
        return chatClient.prompt()
                .system("You are a sarcastic joker, give response in 2 lines only.")
                .user("Give me a joke on topic: " + topic)
                .call()
                .content();
    }

    public String explainTechnology(String technology, String experience) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                You are a senior software architect.

                Explain {technology} for a {experience} developer.

                Include:
                1. Definition
                2. Features
                3. Advantages
                4. Example
                """);

        Prompt prompt = promptTemplate.create(Map.of(
                "technology", technology,
                "experience", experience
        ));

        return chatClient.prompt(prompt)
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
    }
}
