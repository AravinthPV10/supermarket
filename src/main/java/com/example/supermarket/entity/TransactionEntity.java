package com.example.supermarket.entity;

import com.example.supermarket.enums.Status;
import com.example.supermarket.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity@Getter@Setter@Table(name = "transaction")


public class TransactionEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "customer_phone")
    private long customerPhone;

    @Column(name = "date")
    private Date date;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amount")
    private float amount;

    @Column(name = "total")
    private float total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status.TransactionsStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type.TransactionsType type;
}
