package com.knowlode.repository;

import com.knowlode.entity.KnowledgePoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnowledgePointRepository extends JpaRepository<KnowledgePoint, Long> {
    //List<KnowledgePoint> findByCategoryId(Long categoryId);
    //List<KnowledgePoint> findByTitleContaining(String title);
} 