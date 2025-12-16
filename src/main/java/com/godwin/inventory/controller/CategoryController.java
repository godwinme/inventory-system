package com.godwin.inventory.controller;

import com.godwin.inventory.models.Category;
import com.godwin.inventory.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category-list";
    }

    @PostMapping("/new")
    public String saveCategory(@Valid @ModelAttribute Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return "category-form";
            }
            categoryService.createCategory(category);
            redirectAttributes.addFlashAttribute("success", "Category created successfully");
            return "redirect:/categories";
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/categories";
    }

    @GetMapping("/new")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @GetMapping("/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "category-form-edit";
    }

    @PostMapping("/{id}/edit")
    public String editCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult, @PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("Found errors");
                return "category-form-edit";
            }
            categoryService.updateCategory(id, category);
            redirectAttributes.addFlashAttribute("success", "Category updated successfully");
            return "redirect:/categories";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
            return "redirect:/categories";
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/categories";
    }
}