package com.knowlode.repository;

import com.knowlode.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findByKnowledgePointId(Long knowledgePointId);
    //List<Comment> findByUserId(Long userId);
} 