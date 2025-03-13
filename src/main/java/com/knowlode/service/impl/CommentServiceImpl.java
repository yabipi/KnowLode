package com.knowlode.service.impl;

import com.knowlode.dto.CommentDTO;
import com.knowlode.entity.Comment;
import com.knowlode.entity.KnowledgePoint;
import com.knowlode.entity.User;
import com.knowlode.repository.CommentRepository;
import com.knowlode.repository.KnowledgePointRepository;
import com.knowlode.repository.UserRepository;
import com.knowlode.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final KnowledgePointRepository knowledgePointRepository;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        //Comment comment = new Comment();
        //BeanUtils.copyProperties(commentDTO, comment, "id");
        //
        //User user = userRepository.findById(commentDTO.getUserId())
        //        .orElseThrow(() -> new EntityNotFoundException("User not found"));
        //comment.setUser(user);
        //
        //KnowledgePoint knowledgePoint = knowledgePointRepository.findById(commentDTO.getKnowledgePointId())
        //        .orElseThrow(() -> new EntityNotFoundException("Knowledge point not found"));
        //comment.setKnowledgePoint(knowledgePoint);
        //
        //comment = commentRepository.save(comment);
        //return convertToDTO(comment);
        return null;
    }

    @Override
    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        
        comment.setContent(commentDTO.getContent());
        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return convertToDTO(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByKnowledgePoint(Long knowledgePointId) {
        //return commentRepository.findByKnowledgePointId(knowledgePointId).stream()
        //        .map(this::convertToDTO)
        //        .collect(Collectors.toList());
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByUser(Long userId) {
        //return commentRepository.findByUserId(userId).stream()
        //        .map(this::convertToDTO)
        //        .collect(Collectors.toList());
        return null;
    }

    private CommentDTO convertToDTO(Comment comment) {
        //CommentDTO dto = new CommentDTO();
        //BeanUtils.copyProperties(comment, dto);
        //dto.setUserId(comment.getUser().getId());
        //dto.setKnowledgePointId(comment.getKnowledgePoint().getId());
        //return dto;
        return null;
    }
} 