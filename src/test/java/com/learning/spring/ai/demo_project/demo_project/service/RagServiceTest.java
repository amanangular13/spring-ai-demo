package com.learning.spring.ai.demo_project.demo_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RagServiceTest {

    @Autowired
    private PdfIngestionService pdfIngestionService;

    @Autowired
    private RagService ragService;

    @Test
    void shouldAnswerQuestionUsingPdf() {

        pdfIngestionService.ingestPdf();

        String response = ragService.askQuestion(
                "What is Dependency Injection?"
        );

        System.out.println(response);
    }
}