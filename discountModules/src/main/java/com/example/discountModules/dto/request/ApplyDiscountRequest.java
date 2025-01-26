package com.example.discountModules.dto.request;

import java.util.List;

import com.example.discountModules.dto.response.ItemResponse;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyDiscountRequest extends BaseRequest {

    @NotNull(message = "point is required")
    private Integer point;
    @NotEmpty(message = "items is required")
    private List<ItemResponse> items;
    @NotEmpty(message = "discount is required")
    private List<DiscountCreateRequest> discount;

}
