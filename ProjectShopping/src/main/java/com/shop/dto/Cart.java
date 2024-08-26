package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

public class Cart {

    @Id
    @GeneratedValue
    private int cartId;

    @OneToOne
    @JoinColumn(name = "cutomerId")
    @JsonBackReference
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartLineItem> cartLineItem;
}
