package com.example.supermarket.mapper;

import com.example.supermarket.dto.PointDto;
import com.example.supermarket.entity.PointEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointMapper {
    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);
    PointDto toPointDto(PointEntity pointEntity);
    PointEntity toPointEntity(PointDto pointDto);
}
