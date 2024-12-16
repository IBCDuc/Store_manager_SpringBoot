package com.example.demo.service;

import com.example.demo.DTO.InventoryDTO;
import com.example.demo.DTO.OrderSummaryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Products;
import com.example.demo.repository.ProductRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<InventoryDTO> getInventory(Date toDate) {
    List<Object[]> rawData = productRepository.getInventory(toDate); // Query trả về danh sách Object[]

        // Map để nhóm sản phẩm theo đơn hàng
    Map<Integer, InventoryDTO> orderMap = new HashMap<>();
        for (Object[] row : rawData) {
            Integer productId = (Integer) row[0];
            String productName = (String) row[1];
            Integer productPrice = (Integer) row[2];
            Integer totalStockAdded = (Integer) row[3];
            Integer totalStockSold = (Integer) row[4];
            Integer stockRemaining = (Integer) row[5];
            InventoryDTO order = new InventoryDTO(productId, productName, productPrice, totalStockAdded, totalStockSold, stockRemaining);
            orderMap.put(productId, order);
        }
        return new ArrayList<>(orderMap.values());
    }
}
