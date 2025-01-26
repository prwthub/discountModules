package com.example.discountModules.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse extends BaseResponse {

    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;

}
