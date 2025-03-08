package com.knowlode.service.impl;

import com.knowlode.dto.QuestionDTO;
import com.knowlode.entity.Category;
import com.knowlode.entity.KnowledgePoint;
import com.knowlode.entity.Question;
import com.knowlode.repository.CategoryRepository;
import com.knowlode.repository.KnowledgePointRepository;
import com.knowlode.repository.QuestionRepository;
import com.knowlode.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final KnowledgePointRepository knowledgePointRepository;

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question, "id", "knowledgePoints");
        
        Category category = categoryRepository.findById(questionDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        question.setCategory(category);

        if (questionDTO.getKnowledgePointIds() != null) {
            List<KnowledgePoint> knowledgePoints = knowledgePointRepository.findAllById(questionDTO.getKnowledgePointIds());
            question.setKnowledgePoints(knowledgePoints);
        }

        question = questionRepository.save(question);
        return convertToDTO(question);
    }

    @Override
    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        
        BeanUtils.copyProperties(questionDTO, question, "id", "knowledgePoints");
        
        Category category = categoryRepository.findById(questionDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        question.setCategory(category);

        if (questionDTO.getKnowledgePointIds() != null) {
            List<KnowledgePoint> knowledgePoints = knowledgePointRepository.findAllById(questionDTO.getKnowledgePointIds());
            question.setKnowledgePoints(knowledgePoints);
        }

        question = questionRepository.save(question);
        return convertToDTO(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new EntityNotFoundException("Question not found");
        }
        questionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        return convertToDTO(question);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> getQuestionsByCategory(Long categoryId) {
        return questionRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> searchQuestions(String keyword) {
        return questionRepository.findByTitleContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> getQuestionsByKnowledgePoint(Long knowledgePointId) {
        KnowledgePoint knowledgePoint = knowledgePointRepository.findById(knowledgePointId)
                .orElseThrow(() -> new EntityNotFoundException("Knowledge point not found"));
        return knowledgePoint.getQuestions().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        BeanUtils.copyProperties(question, dto);
        dto.setCategoryId(question.getCategory().getId());
        dto.setKnowledgePointIds(question.getKnowledgePoints().stream()
                .map(KnowledgePoint::getId)
                .collect(Collectors.toList()));
        return dto;
    }
} 