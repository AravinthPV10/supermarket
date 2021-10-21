package com.example.supermarket.service.serviceImpl;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.entity.BrandEntity;
import com.example.supermarket.exception.ResourceExistException;
import com.example.supermarket.exception.ResourceNotFoundException;
import com.example.supermarket.mapper.BrandMapper;
import com.example.supermarket.repository.BrandRepository;
import com.example.supermarket.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;


    @Override
    public BrandDto addBrand(BrandDto brandDto) {

        BrandEntity brandEntity = brandRepository.getBrandEntityByName(brandDto.getName());
        if(brandEntity!=null)
            throw new ResourceExistException("Brand Exist.");

        if(brandDto.getName()==null)
            throw new RuntimeException("Name cannot be null");

        brandEntity =  BrandMapper.INSTANCE.toBrandEntity(brandDto);
        brandEntity = brandRepository.save(brandEntity);
        return BrandMapper.INSTANCE.toBrandDto(brandEntity);

    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        if(!brandRepository.existsById(brandDto.getId()))
            throw new ResourceNotFoundException("Brand does not exist.");

        BrandEntity brandEntity = brandRepository.findById(brandDto.getId()).orElse(null);
        if (brandEntity==null)
            throw new RuntimeException("Request body does not exist.");
            if(brandDto.getName()!=null)
                brandEntity.setName(brandDto.getName());
            brandRepository.save(brandEntity);
        return BrandMapper.INSTANCE.toBrandDto(brandEntity);
    }

    @Override
    public BrandDto showBrand(int id) {
        if(!brandRepository.existsById(id))
            throw new ResourceNotFoundException("Brand does not exist.");

        BrandEntity brandEntity = brandRepository.getById(id);
        return BrandMapper.INSTANCE.toBrandDto(brandEntity);
    }

    @Override
    public List<BrandDto> showAllBrand() {
        return Optional.of(brandRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDto)
                .collect(Collectors.toList());
    }
}
