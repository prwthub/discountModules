package com.example.discountModules.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateRequest extends BaseRequest {

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Category is required")
    private String category;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private double price;
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private int quantity;

}
