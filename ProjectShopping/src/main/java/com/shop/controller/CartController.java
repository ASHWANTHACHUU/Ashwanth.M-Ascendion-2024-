package com.shop.controller;

import com.shop.dto.Cart;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/addToCart") //working
    public void addCart(@RequestParam("customerId") int customerId,@RequestParam("productId") int productId ){
        cartService.addToCart(customerId,productId);
    }

    @PutMapping("/update") //working
    public void updateCart(@RequestParam("customerId") int customerId,@RequestParam("productId") int productId ){
        cartService.updateToCart(customerId,productId);
    }

    @DeleteMapping //working
    public void deleteCart(@RequestParam("customerId") int customerId){
        cartService.removeAllProductsFromCart(customerId);
    }

    @GetMapping("/viewCartById") //working
    public Cart viewCart(@RequestParam("customerId") int customerId){
        return cartService.getCartById(customerId);
    }

    @GetMapping("/displayAllCart") //working
    public List<Cart> displayAllCart(){
        return cartService.getAllCarts();
    }

}
