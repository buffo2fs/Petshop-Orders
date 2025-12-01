package com.lucas.petshop.model;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Scanner;

@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "client")
    private String client;

    @Column(name = "dateTime")
    private Timestamp dateTime;

    @Column(name = "status")
    private String status;

    public Order() {

    }

    public Order(Integer quantity, String client, Timestamp dateTime, String status) {
        this.quantity = quantity;
        this.client = client;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Timestamp getDateTime() {
        return this.dateTime;
    }

    public void setDate(Timestamp date) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
