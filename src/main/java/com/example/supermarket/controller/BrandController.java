package com.example.supermarket.controller;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.dto.filters.BaseView;
import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.service.serviceImpl.BrandServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandServiceImpl brandService;

    @PostMapping("/add")
    public ResponseEntity<BrandDto> add(@Validated(UserValidator.Create.class) @RequestBody @JsonView({UserView.Create.class}) BrandDto brandDto){

        BrandDto brand = new BrandDto();
        try{
            brand = brandService.addBrand(brandDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<BrandDto> update(@Validated(UserValidator.Create.class) @RequestBody @JsonView({UserView.Update.class}) BrandDto brandDto){

        BrandDto brand = new BrandDto();
        try{
            brand= brandService.updateBrand(brandDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(brand,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getById(@PathVariable("id") int id){

        BrandDto brand = new BrandDto();
        try {
            brand=brandService.showBrand(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(brand,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BrandDto>> getAll(){

        List<BrandDto> brandDtoList = brandService.showAllBrand();
        return new ResponseEntity<>(brandDtoList,HttpStatus.OK);
    }
}
