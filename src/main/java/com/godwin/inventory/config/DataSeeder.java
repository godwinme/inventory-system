package com.godwin.inventory.config;

import com.godwin.inventory.models.Category;
import com.godwin.inventory.models.Product;
import com.godwin.inventory.service.CategoryService;
import com.godwin.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("✅Data seeding starting.....");

        Category electronics = new Category();
        electronics.setName("Electronics");
        electronics.setDescription("Electronic devices and gadgets");

        Category clothing = new Category();
        clothing.setName("Clothing");
        clothing.setDescription("Apparel and fashion items");


        Category books = new Category();
        books.setName("Books");
        books.setDescription("Books and reading materials");


        electronics = categoryService.createCategory(electronics);
        clothing = categoryService.createCategory(clothing);
        books = categoryService.createCategory(books);

        System.out.println("✅ Created 3 categories");

        //Products
        Product phone = new Product();
        phone.setName("I Phone 15");
        phone.setDescription("Apple Iphone 15");
        phone.setCategory(electronics);
        phone.setPrice(new BigDecimal("100"));
        phone.setAvailableQuantity(10);
        productService.createProduct(phone);

        Product laptop = new Product();
        laptop.setName("Macbook Pro 16");
        laptop.setDescription("Apple Macbook pro 16");
        laptop.setCategory(electronics);
        laptop.setPrice(new BigDecimal("2000"));
        laptop.setAvailableQuantity(100);
        productService.createProduct(laptop);

        Product tablet = new Product();
        tablet.setName("IPad AIR");
        tablet.setDescription("Apple IPAD AIR");
        tablet.setCategory(electronics);
        tablet.setPrice(new BigDecimal("1400"));
        tablet.setAvailableQuantity(50);
        productService.createProduct(tablet);

        Product shirt = new Product();
        shirt.setName("Polo shirt");
        shirt.setDescription("New Polo 2025");
        shirt.setCategory(clothing);
        shirt.setPrice(new BigDecimal("10"));
        shirt.setAvailableQuantity(50);
        productService.createProduct(shirt);

        Product pants = new Product();
        pants.setName("Jeans");
        pants.setDescription("New Jeans 2025");
        pants.setCategory(clothing);
        pants.setPrice(new BigDecimal("14"));
        pants.setAvailableQuantity(50);
        productService.createProduct(pants);


        Product shoes = new Product();
        shoes.setName("Nike shoes");
        shoes.setDescription("New Nike 2025");
        shoes.setCategory(clothing);
        shoes.setPrice(new BigDecimal("15"));
        shoes.setAvailableQuantity(70);
        productService.createProduct(shoes);

        Product softwareDesign = new Product();
        softwareDesign.setName("Oreilly");
        softwareDesign.setDescription("New Oreilly 2025");
        softwareDesign.setCategory(books);
        softwareDesign.setPrice(new BigDecimal("12"));
        softwareDesign.setAvailableQuantity(20);
        productService.createProduct(softwareDesign);

        Product art = new Product();
        art.setName("Figma");
        art.setDescription("New Figma 2025");
        art.setCategory(books);
        art.setPrice(new BigDecimal("13"));
        art.setAvailableQuantity(24);
        productService.createProduct(art);

        Product cooking = new Product();
        cooking.setName("Vegeterians");
        cooking.setDescription("New Cooking Veg 2025");
        cooking.setCategory(books);
        cooking.setPrice(new BigDecimal("14"));
        cooking.setAvailableQuantity(40);
        productService.createProduct(cooking);

        System.out.println("✅ Created 9 products");

        System.out.println("✅Data seeding complete");
    }
}
