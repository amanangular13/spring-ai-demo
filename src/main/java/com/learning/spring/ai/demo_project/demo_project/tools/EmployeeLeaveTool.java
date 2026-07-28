package com.learning.spring.ai.demo_project.demo_project.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class EmployeeLeaveTool {

    @Tool(description = "Get remaining leave balance of an employee")
    public String getLeaveBalance(String employeeName) {
        System.out.println(">>> Leave Tool Called");

        if(employeeName.equalsIgnoreCase("Aman")) {
            return """
                    {
                      "name": "Aman",
                      "casualLeave": 5,
                      "sickLeave": 8,
                      "paidLeave": 12
                    }
                    """;
        }

        return "Employee not found";
    }
}