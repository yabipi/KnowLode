package com.knowlode.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name="title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "answer")
    private String answer;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    // 新增字段：提问者ID
    @Column(name = "user_id")
    private Long userId;

    // 新增字段：问题状态（例如：0-未解决，1-已解决）
    @Column(name = "status")
    private Integer status;

    // 新增字段：问题标签（例如：Java, Spring, JPA）
    @Column
    private String tags;

    // 新增字段：问题浏览量
    @Column(name = "view_count")
    private Integer viewCount;

    // 新增字段：问题点赞数
    @Column(name = "like_count")
    private Integer likeCount;

    // 新增字段：问题收藏数
    @Column(name = "favorite_count")
    private Integer favoriteCount;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long id) {
        this.questionId = id;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}