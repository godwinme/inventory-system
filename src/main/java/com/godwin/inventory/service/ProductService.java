package com.godwin.inventory.service;

import com.godwin.inventory.models.Category;
import com.godwin.inventory.models.Product;
import com.godwin.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new RuntimeException("This product already exists");
        }
        // Validate category
        if (product.getCategory() == null) {
            throw new RuntimeException("Category is required");
        }
        product.setCreatedBy("System");
        product.setUpdatedBy("System");
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id:" + id));
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id:" + id));

        if (!updatedProduct.getName().equals(savedProduct.getName())) {
            if (productRepository.existsByName(updatedProduct.getName())) {
                throw new RuntimeException("This product already exists");
            }

        }
        savedProduct.setName(updatedProduct.getName());
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct.setUpdatedBy("System");
        savedProduct.setCategory(updatedProduct.getCategory());
        savedProduct.setAvailableQuantity(updatedProduct.getAvailableQuantity());
        savedProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(savedProduct);

    }

    public void deleteProduct(Long id) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id:" + id));
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getLowStockProducts(Integer threshold) {
        return productRepository.findByAvailableQuantityLessThan(threshold);
    }

}
