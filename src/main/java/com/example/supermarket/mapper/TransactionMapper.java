package com.example.supermarket.mapper;

import com.example.supermarket.dto.TransactionDto;
import com.example.supermarket.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionEntity toTransactionEntity(TransactionDto transactionDto);
    TransactionDto toTransactionDto(TransactionEntity transactionEntity);
}
