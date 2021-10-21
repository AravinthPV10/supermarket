package com.example.supermarket.service;

import com.example.supermarket.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto addTransaction(TransactionDto transactionDto);
    TransactionDto revertTransaction(int transaction_id);
    TransactionDto showTransaction(int id);
    List<TransactionDto> showAllTransaction();

}
