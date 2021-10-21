package com.example.supermarket.controller;

import com.example.supermarket.dto.CustomerDto;
import com.example.supermarket.dto.DeletedDto;
import com.example.supermarket.dto.filters.BaseView;
import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.service.serviceImpl.CustomerServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@Validated(UserValidator.Create.class) @RequestBody @JsonView({UserView.Create.class}) CustomerDto customerDto){
        CustomerDto customer = new CustomerDto();
        try {
            customer = customerService.addCustomer(customerDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@Validated(UserValidator.Create.class) @RequestBody @JsonView({UserView.Update.class}) CustomerDto customerDto){

        CustomerDto customer = new CustomerDto();
        try {
            customer = customerService.updateCustomer(customerDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return  new ResponseEntity<>(customer,HttpStatus.OK);
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedDto> deleteCustomer( @PathVariable("id") int id){
        DeletedDto dto = new DeletedDto();
        try {
            dto = customerService.deleteCustomer(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> showCustomer(@PathVariable("id") int id){
        CustomerDto customer = new CustomerDto();
        try {
            customer = customerService.showCustomer(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> showAllCustomer(){
        List<CustomerDto> customerDtoList = customerService.showAllCustomer();
        return new ResponseEntity<>(customerDtoList,HttpStatus.OK);
    }
}
