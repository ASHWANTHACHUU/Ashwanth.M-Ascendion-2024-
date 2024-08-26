package com.shop.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderId;
    private int customerId;
    private int total;

    @OneToMany
    List<OrderLineItem> orderLineItemList;

}
