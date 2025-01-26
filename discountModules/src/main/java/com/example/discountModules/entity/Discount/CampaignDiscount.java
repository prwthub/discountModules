package com.example.discountModules.entity.Discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDiscount extends Discount {

    private int minProductCount;
    private double discountAmount;

    public CampaignDiscount(String id, String name, String description, int minProductCount, double discountAmount) {
        super(id, name, description, "campaign");
        this.minProductCount = minProductCount;
        this.discountAmount = discountAmount;
    }

}
