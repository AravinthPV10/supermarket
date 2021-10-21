package com.example.supermarket.service.serviceImpl;

import com.example.supermarket.dto.CustomerDto;
import com.example.supermarket.dto.DeletedDto;
import com.example.supermarket.entity.BrandEntity;
import com.example.supermarket.entity.CustomerEntity;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.mapper.BrandMapper;
import com.example.supermarket.mapper.CustomerMapper;
import com.example.supermarket.repository.CustomerRepository;
import com.example.supermarket.service.CustomerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public boolean phoneNumberValidator(String validPhoneNumbers) {
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(validPhoneNumbers);
        return matcher.matches();
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {

        if(String.valueOf(customerDto.getPhone()).replaceAll("[^\\d]","").length()!=10){
            throw new RuntimeException("Invalid phone number format.");
        }
        if(customerDto.getEmail()==null)
            customerDto.setEmail("test@admin.com");

        CustomerEntity customerEntity = CustomerMapper.INSTANCE.toCustomerEntity(customerDto);
        customerEntity.setIsDeleted(0);
        try {
            customerEntity = customerRepository.save(customerEntity);
        }
        catch (RuntimeException e){
            throw new RuntimeException("Phone number exist.");
        }
        return CustomerMapper.INSTANCE.toCustomerDto(customerEntity);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        if(!customerRepository.existsById(customerDto.getId()))
            throw new ResourceNotFoundException("Customer does not exist.");

        customerEntity = customerRepository.findById(customerDto.getId()).orElse(null);

        if(customerEntity==null)
            throw new RuntimeException("Request body not found.");

        if(customerDto.getName()!=null)
            customerEntity.setName(customerDto.getName());
        if(customerDto.getPhone()!=0)
            customerEntity.setPhone(customerDto.getPhone());

        return CustomerMapper.INSTANCE.toCustomerDto(customerEntity);
    }

    @Override
    public DeletedDto deleteCustomer(int id) {
        if(!customerRepository.existsById(id))
            throw new ResourceAccessException("Customer not found.");
        customerRepository.findById(id).map(customer ->{
            customer.setIsDeleted(1);
            customerRepository.save(customer);
            return customer;
        }).get();
        DeletedDto dto = new DeletedDto();
        dto.setStatus("Success.");
        return dto;
    }

    @Override
    public CustomerDto showCustomer(int id) {

        if(!customerRepository.existsById(id))
            throw new ResourceAccessException("Customer not found");

        CustomerEntity customerEntity = customerRepository.getById(id);
        return CustomerMapper.INSTANCE.toCustomerDto(customerEntity);
    }

    @Override
    public List<CustomerDto> showAllCustomer() {
        return Optional.of(customerRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(CustomerMapper.INSTANCE::toCustomerDto)
                .collect(Collectors.toList());
    }
}
