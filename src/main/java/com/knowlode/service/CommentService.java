package com.knowlode.service;

import com.knowlode.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO);
    CommentDTO updateComment(Long id, CommentDTO commentDTO);
    void deleteComment(Long id);
    CommentDTO getCommentById(Long id);
    List<CommentDTO> getCommentsByKnowledgePoint(Long knowledgePointId);
    List<CommentDTO> getCommentsByUser(Long userId);
} 