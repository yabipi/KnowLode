package com.knowlode.controller;

import com.knowlode.dto.KnowledgePointDTO;
import com.knowlode.service.KnowledgePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-points")
@RequiredArgsConstructor
public class KnowledgePointController {
    private final KnowledgePointService knowledgePointService;

    @PostMapping
    public ResponseEntity<KnowledgePointDTO> createKnowledgePoint(@RequestBody KnowledgePointDTO knowledgePointDTO) {
        return ResponseEntity.ok(knowledgePointService.createKnowledgePoint(knowledgePointDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KnowledgePointDTO> updateKnowledgePoint(@PathVariable Long id, @RequestBody KnowledgePointDTO knowledgePointDTO) {
        return ResponseEntity.ok(knowledgePointService.updateKnowledgePoint(id, knowledgePointDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKnowledgePoint(@PathVariable Long id) {
        knowledgePointService.deleteKnowledgePoint(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgePointDTO> getKnowledgePoint(@PathVariable Long id) {
        return ResponseEntity.ok(knowledgePointService.getKnowledgePointById(id));
    }

    @GetMapping
    public ResponseEntity<List<KnowledgePointDTO>> getAllKnowledgePoints() {
        return ResponseEntity.ok(knowledgePointService.getAllKnowledgePoints());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<KnowledgePointDTO>> getKnowledgePointsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(knowledgePointService.getKnowledgePointsByCategory(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<KnowledgePointDTO>> searchKnowledgePoints(@RequestParam String keyword) {
        return ResponseEntity.ok(knowledgePointService.searchKnowledgePoints(keyword));
    }
} 