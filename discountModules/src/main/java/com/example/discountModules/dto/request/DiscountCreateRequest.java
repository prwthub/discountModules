package com.example.discountModules.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCreateRequest extends BaseRequest {

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Description is required")
    private String description;
    @NotEmpty(message = "Type is required")
    private String type;

    private Integer minProductCount;
    private String category;
    private Double discountAmount;
    private Double discountPercentage;

}
