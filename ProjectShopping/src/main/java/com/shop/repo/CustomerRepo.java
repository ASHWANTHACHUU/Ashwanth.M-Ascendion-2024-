package com.shop.repo;

import com.shop.dto.Customer;
import com.shop.exception.CustomerNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    public Customer findCustomerByCustomerId(int customerId) throws CustomerNotFoundException;
    public Customer findCustomerByCustomerName(String customerName) throws CustomerNotFoundException;
    public Customer findCustomerByCustomerCity(String customerCity);
    public Customer findCustomerByCustomerPinCode(int customerPinCode);
}
