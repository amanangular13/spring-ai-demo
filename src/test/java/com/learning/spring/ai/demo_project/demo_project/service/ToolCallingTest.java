package com.learning.spring.ai.demo_project.demo_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolCallingTest {

    @Autowired
    private ToolCallingService service;

    @Test
    void employeeFoundTest() {
        String response1 = service.ask("How many leaves does Aman have?");
        System.out.println("================================");
        System.out.println(response1);
    }

    @Test
    void employeeNotFoundTest() {
        String response = service.ask("How many leaves does Rahul have?");
        System.out.println("================================");
        System.out.println(response);
    }
}