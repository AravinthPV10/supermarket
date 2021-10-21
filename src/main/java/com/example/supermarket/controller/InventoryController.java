package com.example.supermarket.controller;

import com.example.supermarket.dto.InventoryDto;
import com.example.supermarket.dto.filters.BaseView;
import com.example.supermarket.dto.validators.UserValidator;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.service.serviceImpl.InventoryServiceImpl;
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
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryServiceImpl inventoryService;

    @Autowired
    ProductRepository productRepository;


    @PostMapping("/add")
    public ResponseEntity<InventoryDto> add(@Validated(UserValidator.Create.class) @RequestBody @JsonView({BaseView.Create.class}) InventoryDto inventoryDto){
        InventoryDto inventory = new InventoryDto();
        try {
            inventory = inventoryService.addInventory(inventoryDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<InventoryDto> update(@Validated(UserValidator.Create.class) @RequestBody @JsonView({BaseView.Update.class}) InventoryDto inventoryDto){
        InventoryDto inventory = new InventoryDto();
        try {
            inventory = inventoryService.updateInventory(inventoryDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(inventory, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getById(@PathVariable("id") int id){
        InventoryDto inventory = new InventoryDto();
        try {
            inventory = inventoryService.showInventory(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<InventoryDto>> getAll(){
        List<InventoryDto> inventoryDtoList = inventoryService.showAllInventory();
        return new ResponseEntity<>(inventoryDtoList, HttpStatus.OK);
    }
}