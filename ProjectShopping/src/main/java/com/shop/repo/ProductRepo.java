package com.shop.repo;

import com.shop.dto.Product;
import com.shop.exception.ProductNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    public List<Product> findByProductName(String productName) throws ProductNotFoundException;
    public Product findByProductId(int productId) throws ProductNotFoundException;
    public List<Product> findByProductPriceGreaterThan(int price) ;
    public List<Product> findByProductPriceLessThan(int price);
    public List<Product> findByCategory(String category) throws ProductNotFoundException;

}
