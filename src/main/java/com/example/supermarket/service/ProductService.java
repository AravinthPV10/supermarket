package com.example.supermarket.service;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    ProductDto showProduct(int id);
    List<ProductDto> getByBrand(int brandId);
    List<ProductDto> showAllProduct();
}
