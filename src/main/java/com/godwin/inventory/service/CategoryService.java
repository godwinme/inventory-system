package com.godwin.inventory.service;

import com.godwin.inventory.models.Category;
import com.godwin.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category already exists");
        }
        category.setCreatedBy("system");
        category.setUpdatedBy("system");
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category savedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        if (!updatedCategory.getName().equals(savedCategory.getName())) {
            if (categoryRepository.existsByName(updatedCategory.getName())) {
                throw new RuntimeException(updatedCategory.getName() + " already exists");
            }
        }

        savedCategory.setName(updatedCategory.getName());
        savedCategory.setDescription(updatedCategory.getDescription());
        savedCategory.setUpdatedBy("System");

        return categoryRepository.save(savedCategory);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        if (!category.getProducts().isEmpty()) {
            throw new RuntimeException("Cannot delete category with existing products");
        }
        categoryRepository.deleteById(id);
    }
}
