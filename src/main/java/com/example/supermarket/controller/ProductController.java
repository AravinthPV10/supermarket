package com.example.supermarket.controller;



import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.dto.filters.BaseView;
import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;

import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.service.serviceImpl.ProductServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDto> add(@Validated(UserValidator.Create.class) @RequestBody @JsonView({UserView.Create.class}) ProductDto productDto){
        ProductDto product = new ProductDto();
        try {
            product = productService.addProduct(productDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<ProductDto> update(@Validated(UserValidator.Create.class) @RequestBody @JsonView({UserView.Update.class}) ProductDto productDto){

        try {

            return new ResponseEntity<>(productService.updateProduct(productDto),HttpStatus.OK);

        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") int id){
        ProductDto productDto = new ProductDto();
        try {
            productDto = productService.showProduct(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<List<ProductDto>> getByBrand(@PathVariable("id") int id){
        List<ProductDto> productDtoList= new ArrayList<>();
        try{
            productDtoList = productService.getByBrand(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(productDtoList,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAll(){
        List<ProductDto> productDtoList = productService.showAllProduct();
        return new ResponseEntity<>(productDtoList,HttpStatus.OK);
    }
}