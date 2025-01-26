package com.example.discountModules.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.discountModules.dto.request.ItemAddRequest;
import com.example.discountModules.dto.request.ItemUpdateRequest;
import com.example.discountModules.dto.response.BaseResponse;
import com.example.discountModules.dto.response.ItemResponse;

@Service
public interface ItemService {

    public List<ItemResponse> getAllItems();

    public BaseResponse addItem(ItemAddRequest request);

    public BaseResponse updateItem(String id, ItemUpdateRequest request);

    public BaseResponse deleteItem(String id);

}
