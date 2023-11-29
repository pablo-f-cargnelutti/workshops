package edu.workana.web.testing.service.design;

import edu.workana.web.testing.model.Category;
import edu.workana.web.testing.model.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(String categoryId);

    Category save(CategoryRequest categoryRequest);
}
