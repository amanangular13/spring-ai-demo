package com.learning.spring.ai.demo_project.demo_project.service;

import com.learning.spring.ai.demo_project.demo_project.tools.EmployeeLeaveTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToolCallingService {

    private final ChatClient chatClient;
    private final EmployeeLeaveTool employeeLeaveTool;

    public String ask(String question) {
        return chatClient
                .prompt()
                .user(question)
                .tools(employeeLeaveTool)
                .call()
                .content();
    }
}