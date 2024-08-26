package com.shop.service;

import com.shop.dto.Cart;
import com.shop.dto.Customer;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.ProductNotFoundException;

import java.util.List;

public interface CartService {
    public void addToCart(int customerId, int productId) throws CustomerNotFoundException, ProductNotFoundException;
    public String updateToCart(int customerId, int productId);//4
    public Cart getCartById(int customerId);//2
    public void removeAllProductsFromCart(int customerId);
    public List<Cart> getAllCarts();
}
