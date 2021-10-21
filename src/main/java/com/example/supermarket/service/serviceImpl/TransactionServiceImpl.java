package com.example.supermarket.service.serviceImpl;

import com.example.supermarket.dto.CustomerDto;
import com.example.supermarket.dto.InventoryDto;
import com.example.supermarket.dto.TransactionDto;
import com.example.supermarket.entity.TransactionEntity;
import com.example.supermarket.enums.Status;
import com.example.supermarket.mapper.TransactionMapper;
import com.example.supermarket.repository.TransactionRepository;
import com.example.supermarket.service.TransactionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PointServiceImpl pointService;

    @Autowired
    InventoryServiceImpl inventoryService;

    @Autowired
    CustomerServiceImpl customerService;

    @Override
    public TransactionDto addTransaction(TransactionDto transactionDto) {
        InventoryDto inventory = inventoryService.showInventory(transactionDto.getProductId());
        if(inventory.getQuantity() < transactionDto.getQuantity())
            throw new RuntimeException("Out of stock.");
        int customerId = 0;
       if (transactionRepository.ifCustomerExist(transactionDto.getCustomerPhone())==0) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setPhone(transactionDto.getCustomerPhone());
            customerService.addCustomer(customerDto);
            customerId= transactionRepository.getCustomerId(transactionDto.getCustomerPhone());
        }
        int quantity = inventory.getQuantity();
        float total;
//        int customerId = transactionRepository.getCustomerId(transactionDto.getCustomerPhone());
        TransactionEntity transaction = TransactionMapper.INSTANCE.toTransactionEntity(transactionDto);
        transaction.setCustomerPhone(customerId);
        total = transactionDto.getQuantity() * transactionDto.getAmount();
        transaction.setTotal(total);
        transaction.setStatus(Status.TransactionsStatus.SUCCESS);
        transaction.setType(transactionDto.getType());
        transaction = transactionRepository.save(transaction);
        pointService.addPoint(transaction);
        quantity = quantity - transactionDto.getQuantity();
        inventory.setQuantity(quantity);
        inventoryService.updateInventory(inventory);
        return TransactionMapper.INSTANCE.toTransactionDto(transaction);
    }

    @Override
    public TransactionDto revertTransaction(int id) {
        TransactionEntity transaction = new TransactionEntity();
        if(transactionRepository.existsById(id)) {
            transaction = transactionRepository.getById(id);
            InventoryDto inventory = inventoryService.showInventory(transaction.getProductId());
            int quantity = inventory.getQuantity();
            quantity = quantity + transaction.getQuantity();
            inventory.setQuantity(quantity);
            inventoryService.updateInventory(inventory);
            pointService.revertPoint(transaction);
            transaction.setStatus(Status.TransactionsStatus.REVERTED);
            transactionRepository.save(transaction);
        }
        else
            throw new ResourceAccessException("RESOURCE NOT FOUND");
        return TransactionMapper.INSTANCE.toTransactionDto(transaction);
    }

    @Override
    public TransactionDto showTransaction(int id) {
        TransactionEntity transaction= new TransactionEntity();
        if(transactionRepository.existsById(id)) {
            transaction = transactionRepository.getById(id);
        }
        else
            throw new ResourceAccessException("RESOURCE NOT FOUND");
        return TransactionMapper.INSTANCE.toTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> showAllTransaction() {
        return Optional.of(transactionRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(TransactionMapper.INSTANCE::toTransactionDto)
                .collect(Collectors.toList());
    }
}
