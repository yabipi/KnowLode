package com.knowlode.repository;

import com.knowlode.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Collection<Object> findByTitleContaining(String keyword);
}