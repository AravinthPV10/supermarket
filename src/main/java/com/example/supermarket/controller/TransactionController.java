package com.example.supermarket.controller;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.dto.PointDto;
import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.dto.TransactionDto;
import com.example.supermarket.dto.filters.BaseView;
import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.service.serviceImpl.BrandServiceImpl;
import com.example.supermarket.service.serviceImpl.PointServiceImpl;
import com.example.supermarket.service.serviceImpl.ProductServiceImpl;
import com.example.supermarket.service.serviceImpl.TransactionServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("/add")
    public ResponseEntity<TransactionDto> addTransaction(@Validated(UserValidator.Create.class) @RequestBody @JsonView({UserView.Create.class}) TransactionDto transactionDto){
        TransactionDto transaction = new TransactionDto();
        try {
            transaction = transactionService.addTransaction(transactionDto);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PutMapping("/revert/{id}")
    public ResponseEntity<TransactionDto> revertTransaction(@PathVariable("id") int id){
        TransactionDto transaction = new TransactionDto();
        try {
            transaction = transactionService.revertTransaction(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> showTransaction(@PathVariable("id") int id){
        TransactionDto transactionDto = new TransactionDto();
        try {
            transactionDto = transactionService.showTransaction(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(transactionDto,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> showAllTransaction(){
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        try {
            transactionDtoList=transactionService.showAllTransaction();
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(transactionDtoList,HttpStatus.OK);
    }

    @Autowired
    PointServiceImpl pointService;


    @GetMapping("/point/{id}")
    public ResponseEntity<PointDto> showPoint(@PathVariable("id") int id){

        PointDto point = new PointDto();
        try {
            point= pointService.showPoint(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return new ResponseEntity<>(point,HttpStatus.OK);
    }

    @GetMapping("/all-point")
    public ResponseEntity<List<PointDto>> showAllPoint(){

        List<PointDto> pointDtoList = pointService.showAllPoint();
        return new ResponseEntity<>(pointDtoList,HttpStatus.OK);
    }
}
