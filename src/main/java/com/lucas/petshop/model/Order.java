package com.lucas.petshop.model;


import com.lucas.petshop.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

@Data
@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    @Column (name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "client", nullable = false)
    private String client;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @CreationTimestamp
    @Column(name = "order_creation", nullable = false)
    private LocalDateTime orderCreation;

    @Column(name = "order_update")
    private LocalDateTime orderUpdate;

    @Column(name = "deleted_order", nullable = false)
    private Boolean deletedOrder = false;

}