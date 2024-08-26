package com.shop.service;

import com.shop.dto.Customer;
import com.shop.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer) ; //create
    public List<Customer> getAllCustomers(); //read
    public Customer updateCustomer(Customer customer);
    public void deleteCustomerById(int customerId); // delete
    public Customer findCustomerByCustomerId(int customerId) throws CustomerNotFoundException;
    public Customer findCustomerByCustomerName(String customerName) throws CustomerNotFoundException;
    public Customer findCustomerByCustomerCity(String customerCity)throws CustomerNotFoundException;
    public Customer findCustomerByCustomerPinCode(int customerPinCode);
}
