package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.model.Order;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {
        return new OrderResponseDTO(
                order.getQuantity(),
                order.getClient(),
                order.getStatus(),
                order.getOrderCreation()
        );

    }

    public static Order toEntity(OrderRequestDTO dto){
        Order order = new Order();
        order.setQuantity(dto.quantity());
        order.setClient(dto.client());
        order.setStatus(dto.status());

        return order;

    }
}
