package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAllSupplier() {
        return supplierRepository.findAll();
    }

    public Supplier findBySupplierId(Integer id) {
        return supplierRepository.findById(id).get();
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteById(int id) {
        supplierRepository.deleteById(id);
    }
}
