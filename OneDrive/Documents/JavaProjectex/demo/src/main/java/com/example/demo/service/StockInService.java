package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockInDetailRepository;
import com.example.demo.repository.StockInRepository;

import com.example.demo.entity.StockIn;
import com.example.demo.entity.StockInDetail;
import com.example.demo.DTO.OrderSummaryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.StatusOrderDTO;
import com.example.demo.DTO.StatusReceiptDTO;
import com.example.demo.DTO.StockInSummaryDTO;
import com.example.demo.entity.Products;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockInService {

    @Autowired
    private  StockInRepository stockInRepository;

    @Autowired
    private  StockInDetailRepository stockInDetailRepository;

    @Autowired
    private  ProductRepository productRepository;

    /**
     * Thêm một đơn nhập kho mới và cập nhật sản phẩm trong kho.
     * @param stockIn - thông tin nhập kho.
     * @param stockInDetails - chi tiết sản phẩm trong đơn nhập kho.
     */
    public void addStockIn(StockIn stockIn, List<StockInDetail> stockInDetails) {
        StockIn savedStockIn = stockInRepository.save(stockIn);
        
        for (StockInDetail detail : stockInDetails) {
            detail.setStockIn(savedStockIn);
            stockInDetailRepository.save(detail);

            // Cập nhật số lượng sản phẩm trong kho
            // Supplier chỉ có thể gửi yêu cầu thêm sản phẩm nhập kho, còn admin sẽ là người quyết định nhập kho
            Products product = detail.getProduct();
            int updatedQuantity = product.getQuantity() + detail.getQuantityAdd();
            product.setQuantity(updatedQuantity);
            productRepository.save(product);
        }
    }
    public void addProductSup(Integer quantityAdd, Integer product_id, Integer stockIn) {
        try {
            boolean check = checkStockInDetail( stockIn, product_id);
            if (!check) {
                stockInDetailRepository.insertStockInDetail(quantityAdd, stockIn, product_id);
                Integer quantityDb = productRepository.getQuantityInStock(product_id);
                Integer quantityTotal = quantityDb + quantityAdd;
                productRepository.updateQuantityBasedOnId(quantityTotal , product_id);
            } else {
                Integer quantityDb = productRepository.getQuantityInStock(product_id);
                Integer quantityTotal = quantityDb + quantityAdd;
                productRepository.updateQuantityBasedOnId(quantityTotal , product_id);
            }
            
        } catch(Exception ex) {
            System.out.println("loi o stockinservice "+ ex );
        }

            // Integer quantitySub = stockInDetail.getQuantityAdd();
            // stockInDetail.getProduct().setQuantity(quantityDb + quantitySub);
            
            
            // productRepository.updateQuantityBasedOnId(products.getQuantity(), products.getId());
        
        // return productsList;
    }
    private boolean checkStockInDetail(Integer stockId, Integer productId) {
        List<StockInDetail> stockInAll = stockInDetailRepository.checkStockInDetail(stockId, productId);
        if (stockInAll.isEmpty()) {
            return false;
        }
        return true;
    }

    public void addSupCart(java.util.Date date, Integer user_id) {
        try {
            stockInRepository.insertSupCart(date, user_id);
        } catch(Exception ex) {
            System.out.println("loi ti service" + ex);
        }
        
    }
    public boolean checkSupplier(Integer sup_id) {
        List<Integer> supId = stockInRepository.findSup(sup_id); 
        if (supId.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    // /**
    //  * Cập nhật số lượng sản phẩm trong kho khi có thay đổi trong nhập kho.
    //  * @param productId - mã sản phẩm.
    //  * @param quantity - số lượng thay đổi.
    //  */
    // public void updateProductQuantity(Long productId, int quantity) {
    //     Products product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    //     int updatedQuantity = product.getQuantity() + quantity;
    //     product.setQuantity(updatedQuantity);
    //     productRepository.save(product);
    // }

    /**
     * Lấy tất cả đơn nhập kho
     */
    public List<StockIn> getAllStockIns() {
        return stockInRepository.findAll();
    }
    public List<StockInDetail> getAllStockInDetails() {
        return stockInDetailRepository.findAllStockInDetail();
    }

    public List<StockInSummaryDTO> getStockInSummary(String searchMatirial) {
        List<Object[]> rawData = stockInRepository.searchSummaryStock(searchMatirial); // Query trả về danh sách Object[]

        // Map để nhóm sản phẩm theo đơn hàng
        Map<Integer, StockInSummaryDTO> orderMap = new HashMap<>();

        for (Object[] row : rawData) {

            Integer stockInId = (Integer) row[0];
            Date stockDate = (Date) row[1];
            String status = (String) row[2];
            Integer supplierId = (Integer) row[3];
            String supplierName = (String) row[4];
            String supplierPhone = (String) row[5];
            Integer totalAmount = (Integer) row[6];
            Integer productId = (Integer) row[7];
            String productName = (String) row[8];
            Integer productPrice = (Integer) row[9];
            Integer productQuantity = (Integer) row[10];

            if (productPrice == null || productQuantity == null) {
                productPrice = 0;
                productQuantity = 0;
            }
            Integer productTotal = productPrice * productQuantity;
            
            // Tạo ProductDTO
            ProductDTO product = new ProductDTO(productId, productName, productPrice, productQuantity);

            // Nếu đơn hàng đã tồn tại trong map, thêm sản phẩm vào
            if (orderMap.containsKey(stockInId)) {
                
                StockInSummaryDTO order = orderMap.get(stockInId);

                // Thêm sản phẩm vào danh sách sản phẩm của đơn hàng
                order.getProducts().add(product);
                order.setTotalAmount(order.getTotalAmount() + productTotal);
            } else {
                // Nếu đơn hàng chưa tồn tại, tạo mới và thêm sản phẩm đầu tiên
                List<ProductDTO> products = new ArrayList<>();
                products.add(product);
                totalAmount = productTotal;
                StockInSummaryDTO order = new StockInSummaryDTO(stockInId, stockDate, status, supplierId, supplierName, supplierPhone, totalAmount, products);
                orderMap.put(stockInId, order);
            }
    }

    // Trả về danh sách các đơn hàng
    return new ArrayList<>(orderMap.values());
}

    public List<StatusReceiptDTO> getCountStatusRe() {
        List<Object[]> rawData = stockInRepository.getCountStatusRe(); // Query trả về danh sách Object[]
        Integer id = 0;
        Map<Integer, StatusReceiptDTO> orderMap = new HashMap<>();

        for (Object[] row : rawData) {
            id += 1;
            String receiptStatus = (String) row[0];
            Integer receiptCount = (Integer) row[1];
            Integer receiptQuantity = (Integer) row[2];
            StatusReceiptDTO order = new StatusReceiptDTO(receiptStatus, receiptCount, receiptQuantity);
            orderMap.put(id, order);
        }

        // Trả về danh sách các đơn hàng
        return new ArrayList<>(orderMap.values());
    }


    public void changeStatusCompleted(String status, Integer id) {
        stockInRepository.changeStatusCompleted(status, id);
        return;
    }

    public StockIn checkStatusCart(Integer supplierId) {
        return stockInRepository.checkStatusCart(supplierId);
    }

    public Integer getAllPriceReceipt() {
        return stockInRepository.getAllPriceReceipt();
    }
    public List<StockIn> searchStock(String searchMatiral) {
        try {
            List<StockIn> stockIns =  stockInRepository.searchStock(searchMatiral);
            if (stockIns.isEmpty()) {
                return stockInRepository.findAll();
            }
            return stockIns;
        } catch(Exception ex) {
            System.out.println("loi tai product service");
        }
        return new ArrayList<>();
    }
    
}
