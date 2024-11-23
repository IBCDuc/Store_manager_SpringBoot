package com.example.demo.repository;
import com.example.demo.entity.Products;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer > {

    @Query(value = "SELECT QuantityInStock FROM Products WHERE id = ?1", nativeQuery = true)
    Integer getQuantityInStock(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Products SET QuantityInStock = ?1 WHERE id = ?2", nativeQuery = true)
    void updateQuantityBasedOnId(Integer quantity, Integer id);
    
    @Query(value ="SELECT * FROM Products r WHERE r.name LIKE %:searchMatiral%", nativeQuery = true)
    List<Products> searchProducts(@Param("searchMatiral") String searchMatiral);
}
