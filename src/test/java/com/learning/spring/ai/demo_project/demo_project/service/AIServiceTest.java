package com.learning.spring.ai.demo_project.demo_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {

    @Autowired
    private AIService aiService;

    @Test
    public void testGetJoke() {
        var response = aiService.getJoke("Dogs");
        System.out.println(response);
    }

    @Test
    void shouldExplainSpringAI() {
        String response = aiService.explainTechnology(
                "Spring AI",
                "beginner"
        );

        System.out.println("========== AI Response ==========");
        System.out.println(response);
    }
}
