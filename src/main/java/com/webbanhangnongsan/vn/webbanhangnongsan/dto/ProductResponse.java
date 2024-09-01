package com.webbanhangnongsan.vn.webbanhangnongsan.dto;

import com.webbanhangnongsan.vn.webbanhangnongsan.entity.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class ProductResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long productId;
    String description;
    int discount;
    LocalDateTime enteredDate;
    double price;
    String productImage;
    String productName;
    int quantity;
    Category category;
}

