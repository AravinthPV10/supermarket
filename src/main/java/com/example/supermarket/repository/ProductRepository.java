package com.example.supermarket.repository;

import com.example.supermarket.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    @Query(value = "select * from product where name = :name",nativeQuery = true)
    ProductEntity getProductEntityByName(@Param("name") String name);

    @Query(value = "select * from product where brand_id = :brand_id",nativeQuery = true)
    List<ProductEntity> getProductEntityByBrand(@Param("brand_id") int brand_id);
}
