package com.knowlode.controller;

import com.knowlode.dto.QuestionDTO;
import com.knowlode.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.createQuestion(questionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.updateQuestion(id, questionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(categoryId));
    }

    @GetMapping("/knowledge-point/{knowledgePointId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByKnowledgePoint(@PathVariable Long knowledgePointId) {
        return ResponseEntity.ok(questionService.getQuestionsByKnowledgePoint(knowledgePointId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionDTO>> searchQuestions(@RequestParam String keyword) {
        return ResponseEntity.ok(questionService.searchQuestions(keyword));
    }
} 