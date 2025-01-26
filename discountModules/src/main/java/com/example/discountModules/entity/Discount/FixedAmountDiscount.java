package com.example.discountModules.entity.Discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixedAmountDiscount extends Discount {

    private double discountAmount;

    public FixedAmountDiscount(String id, String name, String description, double discountAmount) {
        super(id, name, description, "fixedAmount");
        this.discountAmount = discountAmount;
    }

}
