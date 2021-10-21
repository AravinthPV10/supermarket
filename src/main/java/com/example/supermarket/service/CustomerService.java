package com.example.supermarket.service;

import com.example.supermarket.dto.CustomerDto;
import com.example.supermarket.dto.DeletedDto;

import java.util.List;

public interface CustomerService {

    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(CustomerDto customerDto);
    DeletedDto deleteCustomer(int id);
    CustomerDto showCustomer(int id);
    List<CustomerDto> showAllCustomer();
}
