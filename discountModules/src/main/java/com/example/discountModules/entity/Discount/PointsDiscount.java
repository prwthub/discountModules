package com.example.discountModules.entity.Discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointsDiscount extends Discount {

    private double discountPercentage;

    public PointsDiscount(String id, String name, String description, double discountPercentage) {
        super(id, name, description, "points");
        this.discountPercentage = discountPercentage;
    }

}
