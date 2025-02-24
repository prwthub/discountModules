package com.example.discountModules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.discountModules.dto.request.ApplyDiscountRequest;
import com.example.discountModules.dto.request.DiscountCreateRequest;
import com.example.discountModules.dto.request.DiscountUpdateRequest;
import com.example.discountModules.dto.response.BaseResponse;
import com.example.discountModules.service.DiscountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping("")
    public ResponseEntity<?> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @PostMapping("")
    public ResponseEntity<?> createDiscount(@Valid @RequestBody DiscountCreateRequest request) {
        // return ResponseEntity.ok(discountService.createDiscount(request));
        BaseResponse response = discountService.createDiscount(request);

        if (response.getCode().equals("400")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable String id,
            @Valid @RequestBody DiscountUpdateRequest request) {
        return ResponseEntity.ok(discountService.updateDiscount(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable String id) {
        return ResponseEntity.ok(discountService.deleteDiscount(id));
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyDiscount(@Valid @RequestBody ApplyDiscountRequest request) {
        return ResponseEntity.ok(discountService.applyDiscount(request));
    }

}
