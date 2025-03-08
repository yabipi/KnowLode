package com.knowlode.service.impl;

import com.knowlode.dto.CategoryDTO;
import com.knowlode.entity.Category;
import com.knowlode.repository.CategoryRepository;
import com.knowlode.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category = categoryRepository.save(category);
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        BeanUtils.copyProperties(categoryDTO, category, "id");
        category = categoryRepository.save(category);
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    BeanUtils.copyProperties(category, categoryDTO);
                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> searchCategories(String keyword) {
        return categoryRepository.findByNameContaining(keyword).stream()
                .map(category -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    BeanUtils.copyProperties(category, categoryDTO);
                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }
} 