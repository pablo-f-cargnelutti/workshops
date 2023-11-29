package edu.workana.web.testing.service.impl;

import edu.workana.web.testing.model.Category;
import edu.workana.web.testing.model.CategoryRequest;
import edu.workana.web.testing.repository.CategoryRepository;
import edu.workana.web.testing.service.design.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category save(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .id(UUID.randomUUID().toString())
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        return categoryRepository.save(category);
    }
}
