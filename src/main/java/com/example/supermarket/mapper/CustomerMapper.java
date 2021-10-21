package com.example.supermarket.mapper;

import com.example.supermarket.dto.CustomerDto;
import com.example.supermarket.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    CustomerDto toCustomerDto(CustomerEntity customerEntity);
    CustomerEntity toCustomerEntity(CustomerDto customerDto);
}
