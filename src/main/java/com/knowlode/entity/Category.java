package com.knowlode.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<KnowledgePoint> knowledgePoints;

    @OneToMany(mappedBy = "category")
    private List<Question> questions;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;
} 