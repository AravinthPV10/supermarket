package com.example.supermarket.repository;

import com.example.supermarket.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    @Query(value = "select count(id) from customer where phone= :phone",nativeQuery = true)
    int ifCustomerExist(@Param("phone") long phone);
    @Query(value = "select id from customer where phone= :phone",nativeQuery = true)
    int getCustomerId(@Param("phone") long phone);
//    @Query(value = "",nativeQuery = true)
//    int
}
