package com.example.discountModules.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.discountModules.dto.request.ApplyDiscountRequest;
import com.example.discountModules.dto.request.DiscountCreateRequest;
import com.example.discountModules.dto.request.DiscountUpdateRequest;
import com.example.discountModules.dto.response.ApplyDiscountResponse;
import com.example.discountModules.dto.response.BaseResponse;
import com.example.discountModules.dto.response.DiscountResponse;

@Service
public interface DiscountService {

    public List<DiscountResponse> getAllDiscounts();

    public BaseResponse createDiscount(DiscountCreateRequest request);

    public BaseResponse updateDiscount(String id, DiscountUpdateRequest request);

    public BaseResponse deleteDiscount(String id);

    public ApplyDiscountResponse applyDiscount(ApplyDiscountRequest request);

}
