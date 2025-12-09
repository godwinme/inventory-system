package com.godwin.inventory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 50, message = "Name should have characters length from 5 to 50")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    @Size(max = 255, message = "Description too long")
    private String description;

    @NotNull(message = "Cannot be null")
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull(message = "Cannot be null")
    @Column(name = "available_quantity", nullable = false)
    @Min(value = 0)
    private Integer availableQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category is mandatory")
    private Category category;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

