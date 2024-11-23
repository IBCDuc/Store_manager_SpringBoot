package com.example.demo.service;

import com.example.demo.entity.Products;
import com.example.demo.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Products> findAllProducts() {
        return productRepository.findAll();
    }

    public Products findByProductsId(Integer id) {
        return productRepository.findById(id).get();
    }

    public Products save(Products products) {
        return productRepository.save(products);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public List<Products> searchProducts(String searchMatiral) {
        try {
            List<Products> products =  productRepository.searchProducts(searchMatiral);
            if (products.isEmpty()) {
                return productRepository.findAll();
            }
            return products;
        } catch(Exception ex) {
            System.out.println("loi tai product service");
        }
        return new ArrayList<>();
    }

}
