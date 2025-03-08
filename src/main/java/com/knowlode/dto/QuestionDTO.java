package com.knowlode.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    
    @NotBlank(message = "标题不能为空")
    private String title;
    
    @NotBlank(message = "内容不能为空")
    private String content;
    
    @NotBlank(message = "答案不能为空")
    private String answer;
    
    @NotNull(message = "分类不能为空")
    private Long categoryId;
    
    private List<Long> knowledgePointIds;
} 