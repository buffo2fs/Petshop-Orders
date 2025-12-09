package com.lucas.petshop.dto;

import com.lucas.petshop.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponseDTO (
        Integer quantity,
        String client,
        OrderStatus status,
        LocalDateTime orderCreation
){}