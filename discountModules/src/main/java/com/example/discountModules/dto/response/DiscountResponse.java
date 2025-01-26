package com.example.discountModules.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountResponse extends BaseResponse {

    private String id;
    private String name;
    private String description;
    private String type;

    private Integer minProductCount;
    private String category;
    private Double discountAmount;
    private Double discountPercentage;

}
