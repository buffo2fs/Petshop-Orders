package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.model.Order;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {
        return new OrderResponseDTO(
                order.getTotalItemsCount(),
                order.getClient(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getOrderCreation()
        );

    }

    public static Order toEntity(OrderRequestDTO dto){
        Order order = new Order();
        order.setTotalItemsCount(dto.totalItemsCount());
        order.setTotalAmount(dto.totalAmount());
        order.setClient(dto.client());
        order.setStatus(dto.status());

        return order;

    }
}
