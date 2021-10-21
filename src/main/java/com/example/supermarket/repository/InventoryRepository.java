package com.example.supermarket.repository;

import com.example.supermarket.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Integer> {

    @Query(value = "select * from inventory where product_id = :product_id",nativeQuery = true)
    InventoryEntity getInventoryId(@Param("product_id") int product_id);
}
