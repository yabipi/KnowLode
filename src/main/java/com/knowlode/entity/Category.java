package com.knowlode.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    //private List<Question> questions;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //public List<KnowledgePoint> getKnowledgePoints() {
    //    return knowledgePoints;
    //}

    //public void setKnowledgePoints(List<KnowledgePoint> knowledgePoints) {
    //    this.knowledgePoints = knowledgePoints;
    //}

    //public List<Question> getQuestions() {
    //    return questions;
    //}

    //public void setQuestions(List<Question> questions) {
    //    this.questions = questions;
    //}

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}