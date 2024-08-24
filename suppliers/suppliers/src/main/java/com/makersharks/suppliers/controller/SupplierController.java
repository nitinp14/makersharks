package com.makersharks.suppliers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makersharks.suppliers.entity.Supplier;
import com.makersharks.suppliers.entity.enums.ManufacturingProcess;
import com.makersharks.suppliers.entity.enums.NatureOfBusiness;
import com.makersharks.suppliers.services.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public ResponseEntity<Page<Supplier>> querySuppliers(
        @RequestParam String location,
        @RequestParam NatureOfBusiness natureOfBusiness,
        @RequestParam ManufacturingProcess manufacturingProcess,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> suppliers = supplierService.findSuppliers(location, natureOfBusiness, manufacturingProcess, pageable);
        
        return ResponseEntity.ok(suppliers);
    }
}
