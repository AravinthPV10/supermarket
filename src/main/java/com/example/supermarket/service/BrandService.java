package com.example.supermarket.service;

import com.example.supermarket.dto.BrandDto;

import java.util.List;

public interface BrandService {

    BrandDto addBrand(BrandDto brandDto);
    BrandDto updateBrand(BrandDto brandDto);
    BrandDto showBrand(int id);
    List<BrandDto> showAllBrand();
}
