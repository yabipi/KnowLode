package com.knowlode.service;

import com.knowlode.dto.QuestionDTO;
import com.knowlode.entity.Question;
import com.knowlode.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    public QuestionDTO createQuestion(QuestionDTO questionDTO);

    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO);

    public Question getQuestionById(Long id);

    public Page<Question> getQuestions(int pageNo, int pageSize);

    //public List<QuestionDTO> getAllQuestions();
    //public Question getQuestionById(Long id);

    public List<QuestionDTO> getQuestionsByCategory(Long categoryId);

    //public List<QuestionDTO> searchQuestions(String keyword);

    public List<QuestionDTO> getQuestionsByKnowledgePoint(Long knowledgePointId);

    public Question saveQuestion(Question question);

    public void deleteQuestion(Long id);
}