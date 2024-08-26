package com.shop.service;

import com.shop.dto.Product;
import com.shop.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public List<Product> getAllProducts();
    public Product updateProduct(Product product) ;
    public Product findByProductId(int productId) throws ProductNotFoundException;
    public List<Product> findByProductPriceGreaterThan(int price);
    public List<Product> findByProductPriceLessThan(int price);
    public void deleteProductByProductId(int productId);
    public List<Product> findByProductName(String productName) throws ProductNotFoundException;
    public List<Product> findByCategory(String category) throws ProductNotFoundException;
}
