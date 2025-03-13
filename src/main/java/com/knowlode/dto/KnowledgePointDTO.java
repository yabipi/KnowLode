package com.knowlode.dto;

import lombok.Data;

import java.util.List;

@Data
public class KnowledgePointDTO {
    private Long id;

    private String title;

    private String content;

    private Long categoryId;
    
    private List<Long> questionIds;
} 