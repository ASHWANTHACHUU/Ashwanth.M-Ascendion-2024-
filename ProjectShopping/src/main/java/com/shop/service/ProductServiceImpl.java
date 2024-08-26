package com.shop.service;

import com.shop.exception.ProductNotFoundException;
import com.shop.repo.ProductRepo;
import com.shop.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product findByProductId(int productId) {
        Product product=productRepo.findByProductId(productId);
        if(product==null){
            throw new ProductNotFoundException("Product not found with id: "+productId);
        }
        return product;
    }

    @Override
    public List<Product> findByProductPriceGreaterThan(int price) {
        Product product=productRepo.findByProductId(price);
        if (product==null){
            throw new ProductNotFoundException("Product not found with id: "+price);
        }
        return productRepo.findByProductPriceGreaterThan(price);
    }

    @Override
    public List<Product> findByProductPriceLessThan(int price) {
        Product product=productRepo.findByProductId(price);
        if (product==null){
            throw new ProductNotFoundException("Product not found with id: "+price);
        }
        return productRepo.findByProductPriceLessThan(price);
    }


    @Override
    public void deleteProductByProductId(int productId) {
        Product product = productRepo.findByProductId(productId);
        if (product != null) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        productRepo.deleteById(productId);
    }

    @Override
    public List<Product> findByProductName(String productName) {
         List<Product> products = productRepo.findByProductName(productName);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product not found with name: " + productName);
        }
    return products;
}


    @Override
    public List<Product> findByCategory(String category) {
        List<Product> products= productRepo.findByCategory(category);
        if (products.isEmpty()){
            throw new ProductNotFoundException("Product not found with category: "+category);
        }
        return products;
    }


}
