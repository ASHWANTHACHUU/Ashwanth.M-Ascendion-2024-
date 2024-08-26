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
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderLineItemId;
    private int orderProductId;
    private int orderProductQuantity;
   @ManyToOne
    private Product product;
    private int orderProductPrice;
    private int orderProductTotal;
    private boolean orderStatus;
}
