package com.example.supermarket.repository;

import com.example.supermarket.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Integer> {

    @Query(value = "select * from brand where name = :name",nativeQuery = true)
    BrandEntity getBrandEntityByName(@Param("name")String name);
}
