package com.shop.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
