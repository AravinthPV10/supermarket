package com.example.supermarket.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Entity
@Table(name = "point")
public class PointEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_phone")
    private long customerPhone;

    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "point")
    private int points;

    @Column(name = "status")
    private String status;
}
