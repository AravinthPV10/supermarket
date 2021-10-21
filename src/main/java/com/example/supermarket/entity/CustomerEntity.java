package com.example.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter@Setter
@Entity @Table(name = "customer")
@JsonIgnoreProperties(
        value = "is_deleted",
        allowGetters = true,allowSetters = true
)
public class CustomerEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private long phone;
    @Column(name = "is_deleted")
    private int isDeleted;

    @Column(name = "email")
    private String email;

}
