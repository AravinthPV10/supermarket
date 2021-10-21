package com.example.supermarket.service;

import com.example.supermarket.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    InventoryDto addInventory(InventoryDto inventoryDto);
    InventoryDto updateInventory(InventoryDto inventoryDto);
    InventoryDto showInventory(int id);
    List<InventoryDto> showAllInventory();

}
