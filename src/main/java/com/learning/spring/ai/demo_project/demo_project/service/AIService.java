package com.learning.spring.ai.demo_project.demo_project.service;

import com.learning.spring.ai.demo_project.demo_project.dto.StudentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;

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

    public StudentResult generateStudentResult() {
        return chatClient.prompt()
                .user("""
                        Create a student result.

                        Student Name: Aman
                        Subject: Mathematics
                        Marks: 92

                        Calculate the grade using:
                        - A : Marks >= 90
                        - B : Marks >= 75
                        - C : Marks >= 60
                        - D : Marks < 60

                        Return only the required information.
                        """)
                .call()
                .entity(StudentResult.class);
    }

    public float[] generateEmbedding(String text) {
        return embeddingModel.embed(text);
    }

    public void loadDocuments() {
        vectorStore.add(List.of(
                new Document(
                        "Java is a programming language.",
                        Map.of("category", "Programming", "level", "Beginner")
                ),
                new Document(
                        "Spring Boot is built using Java.",
                        Map.of("category", "Programming", "level", "Intermediate")
                ),
                new Document(
                        "Pizza is an Italian food.",
                        Map.of("category", "Food")
                ),
                new Document(
                        "Football is a popular sport.",
                        Map.of("category", "Sports")
                )
        ));
    }

    public List<Document> search(String query) {
        return vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(3)
                        .build()
        );
    }
}
