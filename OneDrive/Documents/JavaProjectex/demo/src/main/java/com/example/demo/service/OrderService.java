package com.example.demo.service;
import com.example.demo.DTO.StatusOrderDTO;
import com.example.demo.DTO.OrderSummaryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    Integer totalAmountAll = 0;
    public List<Orders> checkAndAddOrder(Integer id, Date date) {
        try {
            List<Orders> order = orderRepository.checkOrder(id);
            if (order.isEmpty()) {
                orderRepository.insertOrder(id, date);
                System.out.println("suc");
            } else {
                Integer order_id = orderRepository.getOrderId(id);
                String status = orderRepository.getStatusOrder(order_id);
                if ("Completed".equals(status)) {
                    orderRepository.insertOrder(id, date);
                }
            }
            
            
            
            return order;
        } catch(Exception ex) {
            System.out.println("AAAAAAAAAAAA" + ex);
            return new ArrayList<>();
        } 
        
    }

    public List<OrderSummaryDTO> getOrderSummary(String id) {
        List<Object[]> rawData = orderRepository.getOrderSummary(id); // Query trả về danh sách Object[]

        // Map để nhóm sản phẩm theo đơn hàng
        Map<Integer, OrderSummaryDTO> orderMap = new HashMap<>();

        for (Object[] row : rawData) {

            Integer orderId = (Integer) row[0];
            Date orderCount = (Date) row[1];
            String orderStatus = (String) row[2];
            Integer customerId = (Integer) row[3];
            String customerName = (String) row[4];
            String customerEmail = (String) row[5];
            Integer totalAmount = (Integer) row[6];
            Integer productId = (Integer) row[7];
            String productName = (String) row[8];
            Integer productPrice = (Integer) row[9];
            Integer productQuantity = (Integer) row[10];

            Integer productTotal = productPrice * productQuantity;
            
            // Tạo ProductDTO
            ProductDTO product = new ProductDTO(productId, productName, productPrice, productQuantity);

            // Nếu đơn hàng đã tồn tại trong map, thêm sản phẩm vào
            if (orderMap.containsKey(orderId)) {
                
                OrderSummaryDTO order = orderMap.get(orderId);

                // Thêm sản phẩm vào danh sách sản phẩm của đơn hàng
                order.getProducts().add(product);
                order.setTotalAmount(order.getTotalAmount() + productTotal);
            } else {
                // Nếu đơn hàng chưa tồn tại, tạo mới và thêm sản phẩm đầu tiên
                List<ProductDTO> products = new ArrayList<>();
                products.add(product);
                totalAmount = productTotal;
                OrderSummaryDTO order = new OrderSummaryDTO(orderId, orderCount, orderStatus, customerId, customerName, customerEmail, totalAmount, products);
                orderMap.put(orderId, order);
            }
        }

        // Trả về danh sách các đơn hàng
        return new ArrayList<>(orderMap.values());
    }
    public List<StatusOrderDTO> getCountStatus() {
        List<Object[]> rawData = orderRepository.getCountStatus(); // Query trả về danh sách Object[]

        
        Integer id = 0;
        Map<Integer, StatusOrderDTO> orderMap = new HashMap<>();

        for (Object[] row : rawData) {
            id += 1;
            String orderStatus = (String) row[0];
            Integer orderCount = (Integer) row[1];
            Integer totalQuantity = (Integer) row[2];
            StatusOrderDTO order = new StatusOrderDTO(orderStatus, orderCount, totalQuantity);
            orderMap.put(id, order);
        }

        // Trả về danh sách các đơn hàng
        return new ArrayList<>(orderMap.values());
    }

    public Integer getAllPriceOrders() {
        return orderRepository.getAllPriceOrder();
    }
}
