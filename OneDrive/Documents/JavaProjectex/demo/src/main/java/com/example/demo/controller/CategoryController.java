package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Products;
import com.example.demo.entity.Users;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;
    // Get all categories
    @GetMapping
    public CategoryDTO getAllCategory() {
        List<Category> category = categoryService.findAll(); // Lấy danh sách người dùng
        long total = category.size(); // Tổng số người dùng (hoặc lấy từ database)
        return new CategoryDTO(category, total);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Category not found"));
        }
    }

    // Add new category
    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Update category
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        Category existingCategory = categoryService.findById(id);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            categoryService.save(existingCategory);
            return ResponseEntity.ok(existingCategory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Category not found"));
        }
    }

    // Delete category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Category deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Category not found"));
        }
    }
    @PostMapping("/{categoryId}/products")
    public ResponseEntity<?> addProductToCategory(@PathVariable Integer categoryId, @RequestBody Products product) {
        Category category = categoryService.findById(categoryId);
        if (category != null) {
            // product.setCategory(category); // Liên kết sản phẩm với danh mục
            Products savedProduct = productService.save(product); // Lưu sản phẩm
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Category not found"));
        }
    }
}