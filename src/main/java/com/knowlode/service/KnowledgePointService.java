package com.knowlode.service;

import com.knowlode.dto.KnowledgePointDTO;
import java.util.List;

public interface KnowledgePointService {
    KnowledgePointDTO createKnowledgePoint(KnowledgePointDTO knowledgePointDTO);
    KnowledgePointDTO updateKnowledgePoint(Long id, KnowledgePointDTO knowledgePointDTO);
    void deleteKnowledgePoint(Long id);
    KnowledgePointDTO getKnowledgePointById(Long id);
    List<KnowledgePointDTO> getAllKnowledgePoints();
    List<KnowledgePointDTO> getKnowledgePointsByCategory(Long categoryId);
    List<KnowledgePointDTO> searchKnowledgePoints(String keyword);
} 