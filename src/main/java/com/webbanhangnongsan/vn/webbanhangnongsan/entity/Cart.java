package com.webbanhangnongsan.vn.webbanhangnongsan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long id;
    private String name;
    private double unitPrice;
    private int quantity;
    private double totalPrice;
    private Product product;
}
