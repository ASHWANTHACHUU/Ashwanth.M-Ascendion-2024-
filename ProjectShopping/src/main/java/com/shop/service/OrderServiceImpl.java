package com.shop.service;

import com.shop.dto.Customer;
import com.shop.dto.Orders;
import com.shop.repo.OrderRepo;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public void addOrder(int customerId, int cartItemId)  {
         orderRepo.addOrder(customerId,cartItemId);
    }

    @Override
    public void addOrderFromProduct(int customerId, int productId) {
        orderRepo.addOrderFromProduct(customerId,productId);
    }

    @Override
    public void deleteProductFromOrder(int customerId, int productId) {
        orderRepo.deleteProductFromOrder(customerId,productId);
    }

    @Override
    public void cancelOrder(int customerId, int productId) {
        orderRepo.cancelOrder(customerId,productId);
    }

    @Override
    public List<Orders> getOrderByCustomerId(int customerId) {
        return orderRepo.getOrderByCustomerId(customerId);
    }

//    @Override
//    public List<Orders> getOrderByCustomerId(int customerId) {
//        Customer customer=
//        return orders;
//
//    }

}
