package com.shop.repo;

import com.shop.dto.Cart;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.ProductNotFoundException;

import java.util.List;

public interface CustomCart {
    public void addToCart(int customerId, int productId)throws CustomerNotFoundException, ProductNotFoundException;//1
    public String removeCart(int customerId, int productId);//4
    public Cart getCartById(int customerId);//2
    public void removeAllProductsFromCart(int customerId);
}
