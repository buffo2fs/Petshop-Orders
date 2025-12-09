package com.lucas.petshop.dto;

import com.lucas.petshop.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponseDTO (
        Integer totalItemsCount,
        String client,
        Integer totalAmount,
        OrderStatus status,
        LocalDateTime orderCreation
){}