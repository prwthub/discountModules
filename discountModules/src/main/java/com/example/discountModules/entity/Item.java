package com.example.discountModules.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {

    private String name;
    private double price;
    private String category;
    private int quantity;

    public Item(String id, String name, double price, String category, int quantity) {
        super(id);
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

}
