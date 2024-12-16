package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Tìm category theo tên
    List<Category> findByName(String name);

    // Tìm category theo ID
    Category findCategoryById(Integer id);
}
