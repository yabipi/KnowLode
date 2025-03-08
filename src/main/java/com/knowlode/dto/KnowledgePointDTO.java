package com.knowlode.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class KnowledgePointDTO {
    private Long id;
    
    @NotBlank(message = "标题不能为空")
    private String title;
    
    @NotBlank(message = "内容不能为空")
    private String content;
    
    @NotNull(message = "分类不能为空")
    private Long categoryId;
    
    private List<Long> questionIds;
} 