package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OrderDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Orders;
import com.example.demo.entity.OrderDetail;
import com.example.demo.exception.InsufficientQuantityException;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/order")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/each-product")
    public List<OrderDetail> getOrderById( @RequestBody Orders orders ) {
        return orderDetailService.findOrderById(orders.getId());
    }
    
    @GetMapping("/all")
    public OrderDTO getAllOrderDetail() {
        List<OrderDetail> orderDetails =  orderDetailService.findAll();
        long total = orderDetails.size();
        return new OrderDTO(orderDetails, total);
    }

    @PostMapping("/order/price")
    // customm theo front trả về id giỏ hàng!!!
    public int getTotalPrice(@RequestBody Map<String, Object> Order_id) {
        Integer id = (Integer) Order_id.get("order_id");
        return orderDetailService.getTotalPrice(id);
    }
    
   @PostMapping("/addInCart")
   public ResponseEntity<String> postMethodName(@RequestBody Map<String, Object> Entity ) {
       Integer id =       (Integer) Entity.get("order_id");
       Integer quantity = (Integer) Entity.get("quantity");
       Integer products = (Integer) Entity.get("product_id");
    try {
        orderDetailService.addProductToCart(quantity, id, products);
    } catch(InsufficientQuantityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    return ResponseEntity.ok("success!!");
   }
   
   @PostMapping("/save")
   public ResponseEntity<?> saveOrder( @RequestBody Map<String, Object> entity ) {
        Integer id = (Integer) entity.get("id");
        Map < String, Object > proEnt = new LinkedHashMap < String, Object > ();
        try {
            orderDetailService.handleDeleteCart(id);
            proEnt.put("message", "Update Successfully!!!");
            return new ResponseEntity<>(proEnt, HttpStatus.OK);
        } catch(Exception ex) {
            proEnt.clear();
            proEnt.put("message", "update fail");
            return new ResponseEntity<>(proEnt, HttpStatus.OK);
        }
   }
   
}
