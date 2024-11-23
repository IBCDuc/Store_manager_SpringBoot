package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import com.example.demo.DTO.OrderSummaryDTO;
import com.example.demo.DTO.OrderSummaryFullDTO;
import com.example.demo.DTO.StatusOrderDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Users;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/manager/orders")
    public OrderSummaryFullDTO getOrderSummary(@RequestParam(required = false) String search ) {
        List<OrderSummaryDTO> order = orderService.getOrderSummary(search); // Lấy danh sách người dùng

        long total = order.size(); // Tổng số người dùng (hoặc lấy từ database)
        return new OrderSummaryFullDTO(order, total);
    }

    @GetMapping("/price/all")
    public Integer getAllPriceOrders() {
        return orderService.getAllPriceOrders();
    }

    @GetMapping("/order/status")
    public List<StatusOrderDTO> getCountStatus() {
        return orderService.getCountStatus();
    }
    

    @PostMapping("/addCart")
    public List<Orders> addOrder(@RequestBody Map<String, Object> userId) {
        Integer id = (Integer) userId.get("id");
                
        
        LocalDate localDate = LocalDate.now();
        Date sqlDate = Date.valueOf(localDate);
        return orderService.checkAndAddOrder(id, sqlDate);
    }


    
    
    
    
}
