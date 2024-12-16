package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Tìm danh mục theo ID
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Tạo mới danh mục
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // Xóa danh mục theo ID
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    // Tìm danh mục theo tên
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
