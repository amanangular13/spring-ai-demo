package com.learning.spring.ai.demo_project.demo_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QuestionAnswerAdvisorServiceTest {

    @Autowired
    private QuestionAnswerAdvisorService service;

    @Autowired
    private VectorStore vectorStore;

    @Test
    void testQuestionAnswerAdvisor() {
        vectorStore.add(
                List.of(
                        new Document("Spring Boot is a Java framework used to build backend applications."),
                        new Document("Spring AI provides integration between Spring applications and AI models."),
                        new Document("Ollama allows developers to run LLM models locally.")
                )
        );

        String response = service.ask("What is Spring AI?");
        System.out.println(response);
    }
}