package com.example.discountModules.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.discountModules.dto.request.ItemAddRequest;
import com.example.discountModules.dto.request.ItemUpdateRequest;
import com.example.discountModules.dto.response.BaseResponse;
import com.example.discountModules.dto.response.ItemResponse;
import com.example.discountModules.entity.Item;
import com.example.discountModules.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    private static List<Item> items = new ArrayList<>();

    static {
        // Add some initial items
        items.add(new Item(UUID.randomUUID().toString(), "เสื้อ", 100, "เสื้อผ้า", 1));
        items.add(new Item(UUID.randomUUID().toString(), "กางเกง", 200, "เสื้อผ้า", 2));
        items.add(new Item(UUID.randomUUID().toString(), "รองเท้า", 300, "เสื้อผ้า", 2));
        items.add(new Item(UUID.randomUUID().toString(), "ตู้เย็น", 3500, "เครื่องใช้ไฟฟ้า", 1));
        items.add(new Item(UUID.randomUUID().toString(), "ทีวี", 5000, "เครื่องใช้ไฟฟ้า", 1));
    }

    @Override
    public List<ItemResponse> getAllItems() {
        List<ItemResponse> itemsResponse = new ArrayList<>();

        for (Item item : this.items) {
            itemsResponse.add(new ItemResponse(item.getId(), item.getName(),
                    item.getCategory(), item.getPrice(), item.getQuantity()));
        }

        return itemsResponse;
    }

    @Override
    public BaseResponse addItem(ItemAddRequest request) {
        BaseResponse response = new BaseResponse();

        if (request.getPrice() < 0 || request.getQuantity() < 0) {
            response.setCode("400");
            response.setStatus("fail");
            Map<String, String> details = Map.of("price", "Price and quantity must be greater than 0");
            response.setDetails(details);

            return response;
        }

        Item items = new Item(UUID.randomUUID().toString(), request.getName(), request.getPrice(),
                request.getCategory(), request.getQuantity());
        this.items.add(items);

        response.setCode("200");
        response.setStatus("success");
        Map<String, String> details = Map.of("detail", "Item has been added successfully");
        response.setDetails(details);

        return response;
    }

    @Override
    public BaseResponse updateItem(String id, ItemUpdateRequest request) {
        BaseResponse response = new BaseResponse();

        Item item = this.items.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
        if (item != null) {

            if (request.getPrice() < 0 || request.getQuantity() < 0) {
                response.setCode("400");
                response.setStatus("fail");
                Map<String, String> details = Map.of("price", "Price and quantity must be greater than 0");
                response.setDetails(details);

                return response;
            }

            item.setName(request.getName());
            item.setPrice(request.getPrice());
            item.setCategory(request.getCategory());
            item.setQuantity(request.getQuantity());

            response.setCode("200");
            response.setStatus("success");
            Map<String, String> details = Map.of("detail", "Item has been updated successfully");
            response.setDetails(details);
        } else {
            response.setCode("404");
            response.setStatus("fail");
            Map<String, String> details = Map.of("detail", "Item not found");
            response.setDetails(details);
        }

        return response;
    }

    @Override
    public BaseResponse deleteItem(String id) {
        BaseResponse response = new BaseResponse();

        Item item = this.items.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
        if (item != null) {
            this.items.remove(item);

            response.setCode("200");
            response.setStatus("success");
            Map<String, String> details = Map.of("detail", "Item has been deleted successfully");
            response.setDetails(details);
        } else {
            response.setCode("404");
            response.setStatus("fail");
            Map<String, String> details = Map.of("detail", "Item not found");
            response.setDetails(details);
        }

        return response;
    }
}
