package com.learning.spring.ai.demo_project.demo_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RagService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public String askQuestion(String question) {

        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(question)
                        .topK(3)
                        .build()
        );

        System.out.println("===== Retrieved Documents =====");

        documents.forEach(document -> {
            System.out.println(document.getText());
            System.out.println(document.getMetadata());
            System.out.println("----------------------------");
        });

        String context = documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        PromptTemplate promptTemplate = new PromptTemplate("""
                You are a Java Backend Interview Assistant.

                Answer the user's question ONLY using the context below.

                If the answer is not present in the context, reply:
                I don't have enough information.

                Context:
                {context}

                Question:
                {question}

                Answer:
                """);

        Prompt prompt = promptTemplate.create(Map.of(
                "context", context,
                "question", question
        ));

        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}