package com.learning.spring.ai.demo_project.demo_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfIngestionService {

    private final VectorStore vectorStore;

    public void ingestPdf() {

        PagePdfDocumentReader reader =
                new PagePdfDocumentReader(
                        new ClassPathResource("Java_Backend_Interview_Notes.pdf")
                );

        List<Document> documents = reader.get();

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(300)
                .withMinChunkSizeChars(150)
                .withMinChunkLengthToEmbed(5)
                .withMaxNumChunks(1000)
                .withKeepSeparator(true)
                .build();

        List<Document> chunks = splitter.apply(documents);

        vectorStore.add(chunks);

        System.out.println("Documents Read : " + documents.size());
        System.out.println("Chunks Created : " + chunks.size());
    }
}