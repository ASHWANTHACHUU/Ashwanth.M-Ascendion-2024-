package com.shop.service;


import com.shop.dto.Orders;
import com.shop.exception.ProductNotFoundException;
import jakarta.persistence.criteria.Order;

import java.util.List;

public interface OrderService {
    public void addOrderFromProduct(int customerId,int productId);
    public void addOrder(int customerId, int cartItemId) ;
    public void deleteProductFromOrder(int customerId,int productId);
    public void cancelOrder(int customerId,int productId);
    public List<Orders> getOrderByCustomerId(int customerId);
}
