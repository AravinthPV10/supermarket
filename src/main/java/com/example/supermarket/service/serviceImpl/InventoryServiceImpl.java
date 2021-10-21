package com.example.supermarket.service.serviceImpl;

import com.example.supermarket.dto.InventoryDto;
import com.example.supermarket.entity.BrandEntity;
import com.example.supermarket.entity.InventoryEntity;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.mapper.BrandMapper;
import com.example.supermarket.mapper.InventoryMapper;
import com.example.supermarket.repository.InventoryRepository;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public InventoryDto addInventory(InventoryDto inventoryDto) {

        if(!productRepository.existsById(inventoryDto.getProductId()))
            throw new ResourceNotFoundException("Product does not exist.");

        InventoryEntity inventoryEntity  = InventoryMapper.INSTANCE.toInventoryDto(inventoryDto);
        inventoryEntity = inventoryRepository.save(inventoryEntity);
        return InventoryMapper.INSTANCE.toInventoryDto(inventoryEntity);
    }

    @Override
    public InventoryDto updateInventory(InventoryDto inventoryDto) {

        InventoryEntity inventoryEntity = inventoryRepository.findById(inventoryDto.getId()).orElse(null);
        if(inventoryEntity==null)
            throw new RuntimeException("Request body not found");

        if(!productRepository.existsById(inventoryDto.getProductId()))
            throw new ResourceNotFoundException("Product does not exist.");

        if(inventoryDto.getProductId()!=0)
            inventoryEntity.setProductId(inventoryDto.getProductId());
        inventoryEntity.setQuantity(inventoryDto.getQuantity());
        inventoryEntity.setId(inventoryDto.getId());
        inventoryRepository.save(inventoryEntity);
        return InventoryMapper.INSTANCE.toInventoryDto(inventoryEntity);
    }

    @Override
    public InventoryDto showInventory(int productId) {
        InventoryEntity inventoryEntity = new InventoryEntity();
        try{
            inventoryEntity= inventoryRepository.getInventoryId(productId);
        }
        catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Inventory item does not exist.");
        }
        return InventoryMapper.INSTANCE.toInventoryDto(inventoryEntity);
    }

    @Override
    public List<InventoryDto> showAllInventory() {
        return Optional.of(inventoryRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(InventoryMapper.INSTANCE::toInventoryDto)
                .collect(Collectors.toList());
    }
}
