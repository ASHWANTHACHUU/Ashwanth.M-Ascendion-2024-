package com.shop.controller;

import com.shop.dto.Product;
import com.shop.exception.ProductNotFoundException;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/add") //working
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/display")//working
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/id") //working
    public Product getProductById(@RequestParam int productId) {
        return productService.findByProductId(productId);
    }
    @GetMapping("/greater") //working
    public List<Product> findByProductPriceGreater(@RequestParam int productPrice) {
        return productService.findByProductPriceGreaterThan(productPrice);
    }
    @GetMapping("/lesser") //working
    public List<Product> findByProductPriceLess(@RequestParam int productPrice) {
        return productService.findByProductPriceLessThan(productPrice);
    }
    @GetMapping("/category") //working
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.findByCategory(category);
    }
    @GetMapping("/productName") //working
    public List<Product> getProductsByProductName(@RequestParam String productName) {
        return productService.findByProductName(productName);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
    @DeleteMapping("/delete") //working
    public void deleteProductByProductId(@RequestParam int productId) {
        productService.deleteProductByProductId(productId);
    }
}
