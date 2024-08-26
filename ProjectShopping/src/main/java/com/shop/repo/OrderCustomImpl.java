package com.shop.repo;

import com.shop.dto.*;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.ProductNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderCustomImpl implements OrderCustom {

    @PersistenceContext
    @Autowired
    EntityManager em;

    @Transactional
    @Override
    public void addOrderFromProduct(int customerId, int productId) { //  directOrder
        Customer customer = em.find(Customer.class, customerId);
        Product product = em.find(Product.class, productId);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        if(product==null){
            throw new ProductNotFoundException("Product not found");
        }
        Orders orders = new Orders();
        orders.setCustomerId(customerId);
        orders.setTotal(orders.getTotal()+product.getProductPrice());
        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        OrderLineItem orderLineItem=new OrderLineItem();
        orderLineItem.setProduct(product);
        orderLineItem.setOrderProductId(productId);
        orderLineItem.setOrderProductQuantity(product.getProductQuantity());
        orderLineItem.setOrderProductPrice(product.getProductPrice());
        orderLineItem.setOrderProductTotal(product.getProductPrice());
        orderLineItem.setOrderStatus(true);
        em.persist(orderLineItem);
        orderLineItemList.add(orderLineItem);
        orders.setOrderLineItemList(orderLineItemList);
        customer.setOrders(orders);
        em.persist(orders);
        em.persist(customer);
        product.setProductQuantity(product.getProductQuantity()-1);
        em.persist(product);
    }

    @Transactional
    @Override
    public void addOrder(int customerId, int cartItemId) {
        Customer customer = em.find(Customer.class, customerId);
        CartLineItem cartLineItem = em.find(CartLineItem.class, cartItemId);
        Orders orders = customer.getOrders();
        if (orders==null){
            orders = new Orders();
            orders.setCustomerId(customerId);
            orders.setTotal(orders.getTotal()+ cartLineItem.getPrice());
            List<OrderLineItem> orderLineItemList = new ArrayList<>();
            OrderLineItem orderLineItem=new OrderLineItem();
            orderLineItem.setOrderProductId(cartLineItem.getProduct().getProductId());
            orderLineItem.setProduct(cartLineItem.getProduct());
            orderLineItem.setOrderProductQuantity(cartLineItem.getProduct().getProductQuantity());
            orderLineItem.setOrderProductPrice(cartLineItem.getPrice());
            orderLineItem.setOrderProductTotal(cartLineItem.getTotalPrice());
            orderLineItem.setOrderStatus(true);
            em.persist(orderLineItem);
            orderLineItemList.add(orderLineItem);
            orders.setOrderLineItemList(orderLineItemList);
        }
        else{
            List<OrderLineItem> orderLineItemList = orders.getOrderLineItemList();
            boolean found = false;
            int orderLineItemId = 0;
            for (OrderLineItem orderLineItem : orderLineItemList) {
                if(orderLineItem.getProduct()== cartLineItem.getProduct()){
                    found = true;
                    orderLineItemId=orderLineItem.getOrderLineItemId();
                }
            }
            if(found){
                for (OrderLineItem orderLineItem : orderLineItemList) {
                    if(orderLineItem.getOrderLineItemId()==orderLineItemId){
                        orderLineItem.setOrderProductQuantity(orderLineItem.getOrderProductQuantity()+ cartLineItem.getProduct().getProductQuantity());
                        orderLineItem.setOrderProductTotal(orderLineItem.getOrderProductTotal()+ cartLineItem.getProduct().getProductPrice());
                        orderLineItem.setOrderStatus(true);
                        break;
                    }
                }
                orders.setTotal(orders.getTotal()+ cartLineItem.getPrice());
                orders.setOrderLineItemList(orderLineItemList);
            }
            else{
                orders.setTotal(orders.getTotal()+ cartLineItem.getPrice());
                OrderLineItem orderLineItem=new OrderLineItem();
                orderLineItem.setOrderProductId(cartLineItem.getProduct().getProductId());
                orderLineItem.setOrderProductQuantity(cartLineItem.getProduct().getProductQuantity());
                orderLineItem.setOrderProductPrice(cartLineItem.getPrice());
                orderLineItem.setProduct(cartLineItem.getProduct());
                orderLineItem.setOrderProductTotal(cartLineItem.getTotalPrice());
                orderLineItem.setOrderStatus(true);
                em.persist(orderLineItem);
                orders.getOrderLineItemList().add(orderLineItem);
            }
        }
        customer.setOrders(orders);
        Cart cart=customer.getCart();
        List<CartLineItem> cartLineItemList =cart.getCartLineItem();
        cartLineItemList.remove(cartLineItem);
        em.persist(customer);
        Product product=em.find(Product.class, cartLineItem.getProduct().getProductId());
        product.setProductQuantity(product.getProductQuantity()- cartLineItem.getProduct().getProductQuantity());
        em.persist(product);
    }

    @Transactional
    @Override
    public void deleteProductFromOrder(int customerId, int productId) {
        Customer customer=em.find(Customer.class, customerId);
        Product product=em.find(Product.class, productId);
        Orders orders=customer.getOrders();
        List<OrderLineItem> orderLineItemList=orders.getOrderLineItemList();
        for (OrderLineItem orderLineItem : orderLineItemList) {
            if(orderLineItem.getProduct().getProductId()==productId){
                if(orderLineItem.getOrderProductQuantity()>1){
                    orderLineItem.setOrderProductTotal(orderLineItem.getOrderProductTotal()-product.getProductQuantity());
                    orderLineItem.setOrderProductQuantity(orderLineItem.getOrderProductQuantity()-1);
                }
                else{
                    orderLineItemList.remove(orderLineItem);
                }
                break;
            }
        }
        orders.setTotal(orders.getTotal()-product.getProductPrice());
        customer.setOrders(orders);
        em.persist(customer);
        product.setProductQuantity(product.getProductQuantity()+1);
        em.persist(product);
    }

    @Transactional
    @Override
    public void cancelOrder(int customerId, int productId) {
        Customer customer = em.find(Customer.class, customerId);
        Product product = em.find(Product.class, productId);
        Orders orders = customer.getOrders();
        List<OrderLineItem> orderLineItemList = orders.getOrderLineItemList();
        for (OrderLineItem orderLineItem : orderLineItemList) {
            if(orderLineItem.getProduct().getProductId()==productId){
                product.setProductQuantity(product.getProductQuantity()-orderLineItem.getOrderLineItemId());
                orderLineItem.setOrderProductTotal(orders.getTotal()-orderLineItem.getOrderLineItemId());
                orderLineItemList.remove(orderLineItem);
                break;
            }
        }

        orders.setOrderLineItemList(orderLineItemList);
        customer.setOrders(orders);
        em.persist(customer);
        em.persist(product);
    }

    @Override
    public List<Orders> getOrderByCustomerId(int customerId) {
       String sql="Select * from orders where customer_id="+"customer_id";
       Query q=em.createNativeQuery(sql,Orders.class);
       List<Orders> list=q.getResultList();
       return list;
    }

}




