package com.knowlode.repository;

import com.knowlode.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategoryId(Long categoryId);
    List<Question> findByTitleContaining(String title);
} 