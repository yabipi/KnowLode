package com.knowlode.controller;

import com.knowlode.dto.CommentDTO;
import com.knowlode.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(id, commentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping("/knowledge-point/{knowledgePointId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByKnowledgePoint(@PathVariable Long knowledgePointId) {
        return ResponseEntity.ok(commentService.getCommentsByKnowledgePoint(knowledgePointId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.getCommentsByUser(userId));
    }
} 