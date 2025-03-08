package com.knowlode.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDTO {
    private Long id;
    
    @NotBlank(message = "评论内容不能为空")
    private String content;
    
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @NotNull(message = "知识点ID不能为空")
    private Long knowledgePointId;
} 