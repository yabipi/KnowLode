package com.knowlode.service;

import com.knowlode.dto.QuestionDTO;
import java.util.List;

public interface QuestionService {
    QuestionDTO createQuestion(QuestionDTO questionDTO);
    QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO);
    void deleteQuestion(Long id);
    QuestionDTO getQuestionById(Long id);
    List<QuestionDTO> getAllQuestions();
    List<QuestionDTO> getQuestionsByCategory(Long categoryId);
    List<QuestionDTO> searchQuestions(String keyword);
    List<QuestionDTO> getQuestionsByKnowledgePoint(Long knowledgePointId);
} 