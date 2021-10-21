package com.example.supermarket.service.serviceImpl;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.entity.BrandEntity;
import com.example.supermarket.entity.ProductEntity;
import com.example.supermarket.exception.ResourceExistException;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.mapper.BrandMapper;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.repository.BrandRepository;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.service.ProductService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto) {

        ProductEntity productEntity=productRepository.getProductEntityByName(productDto.getName());
        if(productEntity!=null)
            throw new ResourceExistException("Product Exist.");

        if(!brandRepository.existsById(productDto.getBrandId()))
            throw new ResourceNotFoundException("Brand does not exist.");

        productEntity = ProductMapper.INSTANCE.toProductEntity(productDto);
        productRepository.save(productEntity);

        return ProductMapper.INSTANCE.toProductDto(productEntity);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        ProductEntity productEntity =  productRepository.findById(productDto.getId()).orElse(null);
        if(productEntity == null)
            throw new RuntimeException("Request body does not exist.");

        if(!brandRepository.existsById(productDto.getBrandId()))
            throw new RuntimeException("The requested ID does not exist.");

        if(productDto.getBrandId()!=0){
            productEntity.setBrandId(productDto.getBrandId());
        }
        if (productDto.getName()!=null){
        productEntity.setName(productDto.getName());
        }
        if (productDto.getPrice()!=0){
            productEntity.setPrice(productDto.getPrice());
        }
        productEntity.setId(productDto.getId());
        productRepository.save(productEntity);
        return ProductMapper.INSTANCE.toProductDto(productEntity);
    }

    @Override
    public ProductDto showProduct(int id) {

        if(!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product does not exist");

        ProductEntity productEntity = productRepository.getById(id);
        return ProductMapper.INSTANCE.toProductDto(productEntity);

    }
    @Override
    public List<ProductDto> getByBrand(int brandId){

        return Optional.of(productRepository.getProductEntityByBrand(brandId))
                .orElse(Collections.emptyList())
                .stream()
                .map(ProductMapper.INSTANCE::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> showAllProduct() {
        return Optional.of(productRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(ProductMapper.INSTANCE::toProductDto)
                .collect(Collectors.toList());
    }
}
