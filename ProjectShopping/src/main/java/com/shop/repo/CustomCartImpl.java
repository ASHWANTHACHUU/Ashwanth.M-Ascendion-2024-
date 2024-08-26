package com.shop.repo;

import com.shop.dto.Cart;
import com.shop.dto.CartLineItem;
import com.shop.dto.Customer;
import com.shop.dto.Product;
import com.shop.exception.CartNotFoundException;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.ProductNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CustomCartImpl implements CustomCart {

    @PersistenceContext
    @Autowired
     EntityManager em;
    @Transactional
    @Override
    public void addToCart(int customerId, int productId) {
        Customer customer = em.find(Customer.class,customerId);
        Product product=em.find(Product.class,productId);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found with this ID ;"+customerId);
        }
        if(product==null){
            throw new ProductNotFoundException("Product not found with this ID :"+productId);
        }
        Cart cart = customer.getCart();
        if(cart!=null) {
            List<CartLineItem> cartLineItemList =cart.getCartLineItem();
            boolean found=false;
            int cartItemId=0;
            for(CartLineItem cartLineItem : cartLineItemList){
                if(cartLineItem.getProduct()==product){
                    cartItemId= cartLineItem.getCartItemId();
                    found=true;
                    break;
                }
            }
            if(found){
                for(CartLineItem cartLineItem : cartLineItemList){
                    if(cartLineItem.getCartItemId()==cartItemId){
                        cartLineItem.setTotalPrice(cartLineItem.getTotalPrice()+ cartLineItem.getPrice());
                        cartLineItem.setQuantity(cartLineItem.getQuantity()+1);
                    }
                }
            }
            else{
                CartLineItem cartLineItem =new CartLineItem();
                cartLineItem.setProduct(product);
                cartLineItem.setQuantity(1);
                cartLineItem.setTotalPrice(product.getProductPrice());
                cartLineItem.setPrice(product.getProductPrice());
                cart.getCartLineItem().add(cartLineItem);
                em.persist(cartLineItem);
            }
            em.persist(cart);
            customer.setCart(cart);
            em.persist(customer);

        }
        else{
            Cart cart2=new Cart();
            cart2.setCustomer(customer);
            Product product1=em.find(Product.class,productId);
            List<CartLineItem> cartLineItemList = new ArrayList<>();
            CartLineItem cartLineItem =new CartLineItem();
            cartLineItem.setProduct(product1);
            cartLineItem.setQuantity(1);
            cartLineItem.setTotalPrice(product1.getProductPrice());
            cartLineItem.setPrice(product1.getProductPrice());
            cartLineItemList.add(cartLineItem);
            em.persist(cartLineItem);
            cart2.setCartLineItem(cartLineItemList);
            em.persist(cart2);
            customer.setCart(cart2);
            em.persist(customer);
        }
    }
    @Transactional
    @Override
    public String removeCart(int customerId, int productId) {
        Customer customer = em.find(Customer.class, customerId);
        Cart cart = customer.getCart();
        if (cart != null) {
            Product product1 = em.find(Product.class, productId);
            List<CartLineItem> cartLineItemList = cart.getCartLineItem();
            boolean found = false;
            int cartItemId = 0;
            for (CartLineItem cartLineItem : cartLineItemList) {
                if (cartLineItem.getProduct() == product1) {
                    cartItemId = cartLineItem.getCartItemId();
                    found = true;
                    break;
                }
            }
            if (found) {
                for (CartLineItem cd : cartLineItemList) {
                    if (cd.getCartItemId() == cartItemId) {

                        if(cd.getQuantity()>0) {
                            cd.setTotalPrice(cd.getTotalPrice() - cd.getPrice());
                            cd.setQuantity(cd.getQuantity() - 1);
                        }
                        else{
                            String sql="DELETE FROM CartItem where quantity<=0";
                            Query q=em.createNativeQuery(sql);
                            q.executeUpdate();
                            return "your product quantity is already zero";
                        }
                    }
                }
                return "removed successfully";
            }
            customer.setCart(cart);
            em.persist(customer);
        }
        return "cart not found";
    }

    @Transactional
    @Override
    public Cart getCartById(int customerId) {
        Customer customer = em.find(Customer.class,customerId);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found with this ID"+customerId);
        }
        Cart cart = customer.getCart();
        return cart;
    }

    @Override
    @Transactional
    public void removeAllProductsFromCart(int customerId) {
        Customer customer = em.find(Customer.class, customerId);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found with id "+customerId);
        }
        Cart cart= customer.getCart();
        if(cart==null) {
            throw new CartNotFoundException("Cart not found with id "+customerId);
        }
            customer.getCart().setCartLineItem(null);
            em.persist(customer);
        }

}

