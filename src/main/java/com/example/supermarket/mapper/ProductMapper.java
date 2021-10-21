package com.example.supermarket.mapper;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity toProductEntity(ProductDto productDto);
    ProductDto toProductDto(ProductEntity productEntity);
}
