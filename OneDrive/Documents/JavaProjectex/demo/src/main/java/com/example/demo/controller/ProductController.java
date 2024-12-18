package com.example.demo.controller;

import com.example.demo.DTO.InventoryDTO;
import com.example.demo.DTO.InventorySummaryDTO;
import com.example.demo.DTO.ProductsDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Products;
import com.example.demo.entity.Users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ProductsDTO getAllUsers() {
        List<Products> product = productService.findAllProducts(); // Lấy danh sách người dùng
        long total = product.size(); // Tổng số người dùng (hoặc lấy từ database)
        return new ProductsDTO(product, total);
    }

    @GetMapping
     public ResponseEntity<?> searchProducts(@RequestParam(required = false) String search) {
         List<Products> products = productService.searchProducts(search);
         ProductsDTO productsDTO = new ProductsDTO(products, products.size());
         return ResponseEntity.ok(productsDTO);
    }
    @GetMapping("/stock")
    public InventorySummaryDTO getInventory(@RequestParam Date toDate) {
        List<InventoryDTO> inventoryDTOs = productService.getInventory(toDate);
        long total = inventoryDTOs.size();
        return new InventorySummaryDTO(inventoryDTOs, total);
    }

    @PostMapping("/category/{categoryId}/save")
        public ResponseEntity<?> addProductToCategory(@PathVariable Integer categoryId, @RequestBody Products product) {
            Category category = categoryService.findById(categoryId);
            product.setCategoryId(categoryId);
            if (category != null) {
                // product.setCategory(category); // Liên kết sản phẩm với danh mục
                Products savedProduct = productService.save(product); // Lưu sản phẩm
                return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Category not found"));
            }
        }
    
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody String entity) {
        Map <String, Object> proEnt = new LinkedHashMap<>();
        try {
            Products products =  productService.findByProductsId(id);
            products.setName("ori");
            productService.save(products);
            proEnt.put("message", "Update data successfully !!");
            return new ResponseEntity<>(proEnt, HttpStatus.OK);
        } catch (Exception ex) {
            proEnt.clear();
            proEnt.put("message", "Something err while update users");
            return new ResponseEntity<>(proEnt, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        Map < String, Object > proEnt = new LinkedHashMap < String, Object > ();
        try {
            productService.deleteById(id);
            proEnt.put("message", "Successfully!!!");
            return new ResponseEntity<>(proEnt, HttpStatus.OK);
        } catch(Exception ex) {
            proEnt.clear();
            proEnt.put("message", "Product data not found");
            return new ResponseEntity<>(proEnt, HttpStatus.NOT_FOUND);
        }

    }
}