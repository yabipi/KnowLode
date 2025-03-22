package com.knowlode.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_question")
public class QuizQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quiz_id")
    private Integer quizId;

    @Column(name = "question_id")
    private Long questionId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getQuizId() {
        return this.quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }


    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }


}
