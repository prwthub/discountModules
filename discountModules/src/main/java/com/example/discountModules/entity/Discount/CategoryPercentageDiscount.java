package com.example.discountModules.entity.Discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPercentageDiscount extends Discount {

    private String category;
    private double discountPercentage;

    public CategoryPercentageDiscount(String id, String name, String description, String category,
            double discountPercentage) {
        super(id, name, description, "category");
        this.category = category;
        this.discountPercentage = discountPercentage;
    }

}
