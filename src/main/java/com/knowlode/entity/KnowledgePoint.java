package com.knowlode.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "knowledge_points")
public class KnowledgePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;


    //@ManyToMany
    //@JoinTable(
    //    name = "knowledge_question_relation",
    //    joinColumns = @JoinColumn(name = "knowledge_point_id"),
    //    inverseJoinColumns = @JoinColumn(name = "question_id")
    //)
    //private List<Question> questions;
    //
    //@OneToMany(mappedBy = "knowledgePoint")
    //private List<Comment> comments;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //public Category getCategory() {
    //    return category;
    //}
    //
    //public void setCategory(Category category) {
    //    this.category = category;
    //}

    //public List<Question> getQuestions() {
    //    return questions;
    //}
    //
    //public void setQuestions(List<Question> questions) {
    //    this.questions = questions;
    //}
    //
    //public List<Comment> getComments() {
    //    return comments;
    //}
    //
    //public void setComments(List<Comment> comments) {
    //    this.comments = comments;
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