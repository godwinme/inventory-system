package com.godwin.inventory.controller;

import com.godwin.inventory.models.Product;
import com.godwin.inventory.service.CategoryService;
import com.godwin.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

    @PostMapping("/new")
    public String createProduct(@Valid @ModelAttribute Product product,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "product-form";
        }

        try {
            productService.createProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product added successfully");
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }

        return "redirect:/products";
    }

    @GetMapping("/new")
    public String showCreateProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-form-edit";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, @PathVariable Long id,
                                RedirectAttributes redirectAttributes,  Model model) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("categories", categoryService.getAllCategories());
                return "product-form-edit";
            }
            productService.updateProduct(id, product);
            redirectAttributes.addFlashAttribute("success", "Product updated successfully");
            return "redirect:/products";
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            return "product-form";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("success", "Product deleted successfully");
            return "redirect:/products";
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/products";  // ✅ Move outside try-catch
    }
}
