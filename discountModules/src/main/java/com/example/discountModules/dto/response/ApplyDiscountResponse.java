package com.example.discountModules.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDiscountResponse extends BaseResponse {

    private double price;
    private double discountAmount;
    private double finalPrice;
    private List<ItemResponse> items;
    private List<DiscountResponse> appliedDiscounts;

}
