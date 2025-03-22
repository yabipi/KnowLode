package com.knowlode.repository;

import com.knowlode.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Collection<Object> findByTitleContaining(String keyword);

    @Query(value = "select question from Question question order by question.createTime desc")
    public Page<Question> listQuestions(Pageable pageable);
}