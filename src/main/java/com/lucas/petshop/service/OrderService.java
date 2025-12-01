package com.lucas.petshop.service;

import com.lucas.petshop.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    long createOrder(Order order);

    void updateOrder(Long id, Order order);

    void deleteOrder(Long id);
}
