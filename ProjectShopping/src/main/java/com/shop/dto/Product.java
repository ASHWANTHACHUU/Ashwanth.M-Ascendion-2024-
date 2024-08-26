package com.shop.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    @Id
    private int productId;
    private String productName;
    private int productPrice;
    private String category;
    private int productQuantity;

}
