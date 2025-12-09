package com.godwin.inventory.repository;

import com.godwin.inventory.models.Category;
import com.godwin.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findByAvailableQuantityLessThan(Integer quantity);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    boolean existsByName(String name);
}
