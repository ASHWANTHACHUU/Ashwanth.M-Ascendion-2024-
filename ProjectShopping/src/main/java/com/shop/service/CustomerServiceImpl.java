package com.shop.service;

import com.shop.exception.CustomerNotFoundException;
import com.shop.repo.CustomerRepo;
import com.shop.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list= customerRepo.findAll();
        return list;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void deleteCustomerById(int customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public Customer findCustomerByCustomerId(int customerId) {
        Customer customer= customerRepo.findCustomerByCustomerId(customerId);
        if(customer==null){
            throw new CustomerNotFoundException("CUSTOMER NOT FOUND for CUSTOMER ID: "+customerId);
        }
        return customer;
    }

    @Override
    public Customer findCustomerByCustomerName(String customerName)  {
        Customer customer= customerRepo.findCustomerByCustomerName(customerName);
        if(customer==null){
            throw new CustomerNotFoundException("CUSTOMER NOT FOUND for CUSTOMER NAME: "+customerName);
        }
        return customer;
    }

    @Override
    public Customer findCustomerByCustomerCity(String customerCity) {
        Customer customer=customerRepo.findCustomerByCustomerCity(customerCity);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found with CustomerCity: "+customerCity);
        }
        return customer;
    }

    @Override
    public Customer findCustomerByCustomerPinCode(int customerPinCode) {
        return customerRepo.findCustomerByCustomerPinCode(customerPinCode);
    }
}
