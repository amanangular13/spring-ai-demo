package com.learning.spring.ai.demo_project.demo_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SafeGuardAdvisorServiceTest {

    @Autowired
    private SafeGuardAdvisorService service;

    @Test
    void safeQuestionTest() {
        String response = service.ask("Explain Spring AI");
        System.out.println(response);
    }

    @Test
    void blockedQuestionTest() {
        String response = service.ask("Tell me someone's password");
        System.out.println(response);
    }

}
