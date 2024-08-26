package com.shop.controller;

import com.shop.dto.Customer;
import com.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add") //working
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/display") //working
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/update") // working
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
    @GetMapping("/id")
    public Customer getCustomerById(@RequestParam int customerId) {
        return customerService.findCustomerByCustomerId(customerId);
    }
    @GetMapping("/customerName")//working
    public Customer getCustomerByCustomerName(@RequestParam String customerName) {
        return customerService.findCustomerByCustomerName(customerName);
    }
    @GetMapping("/customerCity") //working
    public Customer getCustomerByCustomerCity(@RequestParam String customerCity) {
        return customerService.findCustomerByCustomerCity(customerCity);
    }
    @GetMapping("/customerPinCode")//working
    public Customer getCustomerByCustomerPinCode(@RequestParam int customerPinCode) {
        return customerService.findCustomerByCustomerPinCode(customerPinCode);
    }
    @DeleteMapping("/delete") //working
    public void deleteCustomerById(@RequestParam int customerId) {
        customerService.deleteCustomerById(customerId);
    }
}
