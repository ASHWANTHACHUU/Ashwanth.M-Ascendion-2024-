package com.shop.repo;

import com.shop.dto.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> , CustomCart {

}