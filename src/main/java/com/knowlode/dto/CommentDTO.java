package com.knowlode.dto;

import lombok.Data;


@Data
public class CommentDTO {
    private Long id;

    private String content;

    private Long userId;

    private Long knowledgePointId;
} 