package com.example.demo.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.DTO.OrderSummaryDTO;
import com.example.demo.DTO.OrderSummaryFullDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.ProductsDTO;
import com.example.demo.DTO.StatusReceiptDTO;
import com.example.demo.DTO.StockInSummaryDTO;
import com.example.demo.DTO.StockInSummaryFullDTO;
import com.example.demo.entity.Products;
import com.example.demo.entity.StockIn;
import com.example.demo.entity.StockInDetail;
import com.example.demo.service.StockInService;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/stock-in")
public class StockInController {



    @Autowired
    private StockInService stockInService;

    @GetMapping("/detail/all")
    public List<StockInDetail> getAllStockInDetail() {
        return stockInService.getAllStockInDetails();
    }
    @GetMapping("/details")
    public StockInSummaryFullDTO getOrderSummary( @RequestParam(required = false) String search ) {
        List<StockInSummaryDTO> order = stockInService.getStockInSummary(search); // Lấy danh sách người dùng

        long total = order.size(); // Tổng số người dùng (hoặc lấy từ database)
        return new StockInSummaryFullDTO(order, total);
    }

    @GetMapping("/status")
    public List<StatusReceiptDTO> getCountStatusRe() {
        return stockInService.getCountStatusRe();
    }
    
    @GetMapping("/price/all")
    public Integer getAllPriceReceipt() {
        return stockInService.getAllPriceReceipt();
    }
    @GetMapping
    public ResponseEntity<?> searchStock(@RequestParam(required = false) String search) {
        List<StockIn> stockIn = stockInService.searchStock(search);
        return ResponseEntity.ok(stockIn);
    }
    
    
    @PostMapping("/addSupCart")
    public void addSupplierCart(@RequestBody Map<String, Object> supplier_id) {
        Integer getSupId = (Integer) supplier_id.get("id");
        boolean supId = stockInService.checkSupplier(getSupId);
        StockIn stockIn = stockInService.checkStatusCart(getSupId);
        LocalDate localDate = LocalDate.now();

        // Định dạng "yyyy/MM/dd"
        
        
        // Chuyển đổi thành java.sql.Date để lưu vào SQL
        Date sqlDate = Date.valueOf(localDate);
        if (!supId) {
            stockInService.addSupCart(sqlDate , getSupId);
            return;
        }
        if (stockIn == null) {
            stockInService.addSupCart(sqlDate , getSupId);
            return;
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<String> addProductFromSup(@RequestBody  Map<String, Object> stockInDetail) {
        try {
            Integer quantityAdd = (Integer) stockInDetail.get("quantityAdd");
            Integer product = (Integer) stockInDetail.get("product");
            Integer stockIn = (Integer) stockInDetail.get("stockIn");
            // xử lý logic thêm ds sản phẩm 
            stockInService.addProductSup(quantityAdd, product, stockIn  );
        } catch(Exception ex) {
            System.err.println("lỗi tại stockIn controller " + ex);
        }

        return ResponseEntity.ok("add product stockin success!!");
    }

    @PostMapping("/save-product")
    public ResponseEntity<String> comfirmAddProductFormSup(@RequestBody StockInSummaryFullDTO stockInSummaryFullDTO) {
        try {
            List<StockInSummaryDTO> stockInSummaryDTO = stockInSummaryFullDTO.getData(); 
            for (StockInSummaryDTO stockIn : stockInSummaryDTO ) {
                List<ProductDTO> product = stockIn.getProducts();
                Integer stockInId = stockIn.getstockInId();
                for (ProductDTO pro : product) {
                    Integer productId = (Integer) pro.getProductId();
                    Integer quantityAdd = (Integer) pro.getQuantity();
                    stockInService.addProductSup(quantityAdd, productId, stockInId  );
                }
                
            }
        } catch(Exception ex) {
            System.err.println("lỗi tại stockIn controller " + ex);
        }

        return ResponseEntity.ok("add product stockin success!!"); 
    }
    


    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirmStockIn(@PathVariable Integer id, @RequestParam String status) {
        try {
            stockInService.changeStatusCompleted(status, id);
            
        } catch(Exception ex) {
            System.err.println("lỗi tại stockIn controller " + ex);
        }     
        return ResponseEntity.ok("change status success stockin success!!");
        
    }
    
    
    // }   
    public StockIn addProductFromSup(@RequestBody StockIn stockIn) {
        return stockIn;
    } 
    
}