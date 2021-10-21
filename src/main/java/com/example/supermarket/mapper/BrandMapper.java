package com.example.supermarket.mapper;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toBrandDto(BrandEntity brandEntity);
    BrandEntity toBrandEntity(BrandDto brandDto);
}
