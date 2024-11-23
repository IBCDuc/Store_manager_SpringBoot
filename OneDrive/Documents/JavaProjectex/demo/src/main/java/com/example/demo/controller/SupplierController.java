package com.example.demo.controller;

import com.example.demo.entity.Supplier;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.SupplierService;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;


    @GetMapping
    public List<Supplier> getAllProducts() {
        return supplierService.findAllSupplier();
    }

    @PostMapping("/save")

        public  ResponseEntity<?> saveProduct(@RequestBody Supplier supplier) {
            Map <String, Object> prodEnt = new LinkedHashMap<>();
            try {
                supplierService.save(supplier);
                prodEnt.put("message", "successfully!");
                return new ResponseEntity<>(prodEnt, HttpStatus.OK );
            } catch(Exception ex) {
                prodEnt.clear();
                prodEnt.put("message", "Save User Fail");
                return new ResponseEntity<>(prodEnt, HttpStatus.NOT_FOUND);
            }
        }
    @PutMapping("update/{id}")
    public ResponseEntity<?> updatesupplier(@PathVariable int id, @RequestBody String entity) {
        Map <String, Object> proEnt = new LinkedHashMap<>();
        try {
            Supplier suppliers =  supplierService.findBySupplierId(id);
            suppliers.setName("duc123");
            supplierService.save(suppliers);
            proEnt.put("message", "Update data successfully !!");
            return new ResponseEntity<>(proEnt, HttpStatus.OK);
        } catch (Exception ex) {
            proEnt.clear();
            proEnt.put("message", "Something err while update users");
            return new ResponseEntity<>(proEnt, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletesupplier(@PathVariable Integer id) {
        Map < String, Object > proEnt = new LinkedHashMap < String, Object > ();
        try {
            supplierService.deleteById(id);
            proEnt.put("message", "Successfully!!!");
            return new ResponseEntity<>(proEnt, HttpStatus.OK);
        } catch(Exception ex) {
            proEnt.clear();
            proEnt.put("message", "Product data not found");
            return new ResponseEntity<>(proEnt, HttpStatus.NOT_FOUND);
        }

    }
}