package com.example.discountModules.entity.Discount;

import com.example.discountModules.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity {

    private String name;
    private String description;
    private String type;

    public Discount(String id, String name, String description, String type) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
    }
}
