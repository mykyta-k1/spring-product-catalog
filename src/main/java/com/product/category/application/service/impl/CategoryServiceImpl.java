package com.product.category.application.service.impl;

import com.product.category.application.exception.CategoryNotFoundException;
import com.product.category.application.service.contract.CategoryService;
import com.product.category.domain.model.Category;
import com.product.category.infrastructure.dao.CategoryRepository;
import com.product.category.application.dto.resp.CategoryShortResponse;
import com.product.product.application.mapper.CategoryMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public List<CategoryShortResponse> findAllShortCategories() {
        return categoryRepository.findAll().stream()
            .map(categoryMapper::categoryToCategoryShortResponse).toList();
    }
}
