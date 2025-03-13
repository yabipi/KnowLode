package com.knowlode.service.impl;

import com.knowlode.dto.KnowledgePointDTO;
import com.knowlode.entity.Category;
import com.knowlode.entity.KnowledgePoint;
import com.knowlode.entity.Question;
import com.knowlode.repository.CategoryRepository;
import com.knowlode.repository.KnowledgePointRepository;
import com.knowlode.repository.QuestionRepository;
import com.knowlode.service.KnowledgePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class KnowledgePointServiceImpl implements KnowledgePointService {
    private final KnowledgePointRepository knowledgePointRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    @Override
    public KnowledgePointDTO createKnowledgePoint(KnowledgePointDTO knowledgePointDTO) {
        //KnowledgePoint knowledgePoint = new KnowledgePoint();
        //BeanUtils.copyProperties(knowledgePointDTO, knowledgePoint, "id", "questions");
        //
        //Category category = categoryRepository.findById(knowledgePointDTO.getCategoryId())
        //        .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        //knowledgePoint.setCategory(category);
        //
        //if (knowledgePointDTO.getQuestionIds() != null) {
        //    List<Question> questions = questionRepository.findAllById(knowledgePointDTO.getQuestionIds());
        //    knowledgePoint.setQuestions(questions);
        //}
        //
        //knowledgePoint = knowledgePointRepository.save(knowledgePoint);
        //BeanUtils.copyProperties(knowledgePoint, knowledgePointDTO);
        //return knowledgePointDTO;
        return null;
    }

    @Override
    public KnowledgePointDTO updateKnowledgePoint(Long id, KnowledgePointDTO knowledgePointDTO) {
        //KnowledgePoint knowledgePoint = knowledgePointRepository.findById(id)
        //        .orElseThrow(() -> new EntityNotFoundException("Knowledge point not found"));
        //
        //BeanUtils.copyProperties(knowledgePointDTO, knowledgePoint, "id", "questions");
        //
        //Category category = categoryRepository.findById(knowledgePointDTO.getCategoryId())
        //        .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        //knowledgePoint.setCategory(category);
        //
        //if (knowledgePointDTO.getQuestionIds() != null) {
        //    List<Question> questions = questionRepository.findAllById(knowledgePointDTO.getQuestionIds());
        //    knowledgePoint.setQuestions(questions);
        //}
        //
        //knowledgePoint = knowledgePointRepository.save(knowledgePoint);
        //BeanUtils.copyProperties(knowledgePoint, knowledgePointDTO);
        //return knowledgePointDTO;
        return null;
    }

    @Override
    public void deleteKnowledgePoint(Long id) {
        if (!knowledgePointRepository.existsById(id)) {
            throw new EntityNotFoundException("Knowledge point not found");
        }
        knowledgePointRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public KnowledgePointDTO getKnowledgePointById(Long id) {
        KnowledgePoint knowledgePoint = knowledgePointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Knowledge point not found"));
        return convertToDTO(knowledgePoint);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KnowledgePointDTO> getAllKnowledgePoints() {
        return knowledgePointRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<KnowledgePointDTO> getKnowledgePointsByCategory(Long categoryId) {
        //return knowledgePointRepository.findByCategoryId(categoryId).stream()
        //        .map(this::convertToDTO)
        //        .collect(Collectors.toList());
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<KnowledgePointDTO> searchKnowledgePoints(String keyword) {
        //return knowledgePointRepository.findByTitleContaining(keyword).stream()
        //        .map(this::convertToDTO)
        //        .collect(Collectors.toList());
        return null;
    }

    private KnowledgePointDTO convertToDTO(KnowledgePoint knowledgePoint) {
        //KnowledgePointDTO dto = new KnowledgePointDTO();
        //BeanUtils.copyProperties(knowledgePoint, dto);
        //dto.setCategoryId(knowledgePoint.getCategory().getId());
        //dto.setQuestionIds(knowledgePoint.getQuestions().stream()
        //        .map(Question::getId)
        //        .collect(Collectors.toList()));
        //return dto;
        return null;
    }
} 