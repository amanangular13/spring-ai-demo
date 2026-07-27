package com.learning.spring.ai.demo_project.demo_project.dto;

import lombok.Data;

@Data
public class StudentResult {
    private String studentName;
    private String subject;
    private int marks;
    private String grade;
}