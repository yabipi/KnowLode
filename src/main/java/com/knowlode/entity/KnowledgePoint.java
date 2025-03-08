package com.knowlode.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "knowledge_question_relation",
        joinColumns = @JoinColumn(name = "knowledge_point_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    @OneToMany(mappedBy = "knowledgePoint")
    private List<Comment> comments;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;
} 