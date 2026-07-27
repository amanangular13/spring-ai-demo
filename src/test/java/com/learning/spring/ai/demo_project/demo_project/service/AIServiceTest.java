package com.learning.spring.ai.demo_project.demo_project.service;

import com.learning.spring.ai.demo_project.demo_project.dto.StudentResult;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void shouldGenerateStudentResult() {
        StudentResult result = aiService.generateStudentResult();
        System.out.println("===== AI Response =====");
        System.out.println(result);
    }

    @Test
    void shouldGenerateEmbedding() {
        float[] embedding = aiService.generateEmbedding(
                "Spring Boot is awesome"
        );

        System.out.println("Vector Length = " + embedding.length);

        for (int i = 0; i < 10; i++) {
            System.out.println(embedding[i]);
        }
    }

    @Test
    void shouldPerformSimilaritySearch() {

        aiService.loadDocuments();

        List<Document> results = aiService.search("Tell me about Java");

        System.out.println("===== Search Results =====");

        results.forEach(document -> {
            System.out.println(document.getText());
            System.out.println(document.getMetadata());
            System.out.println("----------------------------");
        });
    }
}
