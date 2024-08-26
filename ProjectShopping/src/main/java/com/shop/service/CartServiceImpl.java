package com.shop.service;

import com.shop.dto.Cart;
import com.shop.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepo cartRepo;

    @Override
    public void addToCart(int customerId, int productId) {
        cartRepo.addToCart(customerId,productId);
    }


    @Override
    public String updateToCart(int customerId, int productId) {
        return cartRepo.removeCart(customerId,productId);
    }

    @Override
    public Cart getCartById(int customerId) {
        return cartRepo.getCartById(customerId);
    }

    @Override
    public void removeAllProductsFromCart(int customerId) {
         cartRepo.removeAllProductsFromCart(customerId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }
}
