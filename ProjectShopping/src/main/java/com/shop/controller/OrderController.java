package com.shop.controller;

import com.shop.dto.Orders;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/directorder") //working
    public void orderNow(@RequestParam int customerId,@RequestParam int productId){
         orderService.addOrderFromProduct(customerId,productId);
    }

    @PostMapping("/addorder") //working
    public void cartOrder(@RequestParam int customerId,@RequestParam int cartItemId){
        orderService.addOrder(customerId,cartItemId);
    }

    @DeleteMapping //working
    public void deleteProductFromOrder(@RequestParam int customerId,@RequestParam int productId){
        orderService.deleteProductFromOrder(customerId,productId);
    }
    @GetMapping("/viewallorders") //working
    public List<Orders> viewAllOrders(@RequestParam int customerId){
        List<Orders> order=orderService.getOrderByCustomerId(customerId);
        return order;
    }
    @PutMapping("/cancel")
    public void cancelOrder(@RequestParam int customerId,@RequestParam int productId){
        orderService.cancelOrder(customerId,productId);
    }
}
