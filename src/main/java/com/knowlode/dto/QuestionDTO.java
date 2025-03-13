package com.knowlode.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;

    private String title;

    private String content;

    private String answer;

    private Long categoryId;
    
    private List<Long> knowledgePointIds;
} 