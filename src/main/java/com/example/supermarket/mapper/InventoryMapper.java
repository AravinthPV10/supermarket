package com.example.supermarket.mapper;

import com.example.supermarket.dto.InventoryDto;
import com.example.supermarket.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDto toInventoryDto(InventoryEntity inventoryEntity);
    InventoryEntity toInventoryDto(InventoryDto inventoryDto);

}