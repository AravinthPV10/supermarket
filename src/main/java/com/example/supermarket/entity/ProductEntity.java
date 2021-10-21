package com.example.supermarket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Entity
@Table(name = "product")
public class ProductEntity extends AuditEntity{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "brand_id")
    private int brandId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;

}
