package com.example.supermarket.repository;

import com.example.supermarket.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointRepository extends JpaRepository<PointEntity,Integer> {

    @Query(value = "select * from point where transaction_id = :transaction_id",nativeQuery = true)
    PointEntity getEntityToUpdate(@Param("transaction_id") int transaction_id);
}
