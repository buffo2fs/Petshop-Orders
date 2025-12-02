package com.lucas.petshop.service;


import com.lucas.petshop.repository.OrderRepository;
import com.lucas.petshop.model.Order;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        var startTime = System.currentTimeMillis();

        List<Order> orderList = orderRepository.findAll();

        Timer.measure("[GET ALL ORDERS] - Successfully", startTime);
        return orderList;
    }

    @Override
    public Order getOrderById(Long id){
        var startTime = System.currentTimeMillis();

        Order orders = orderRepository.getById(id);

        Timer.measure("[GET ORDER BY ID] - Succesfully", startTime);
        return orders;

    }

    @Override
    @Transactional
    public long createOrder(Order order){
        var startTime = System.currentTimeMillis();

        Order newOrder = orderRepository.save(order);

        Timer.measure("[CREATE ORDER] - Successfully", startTime);

        return newOrder.getId();
    }

    @Override
    @Transactional
    public void updateOrder(Long id, Order order) {
        var startTime = System.currentTimeMillis();

        Order existingOrder = orderRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ORDER NOT FOUND"));

        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setClient(order.getClient());
        existingOrder.setDate(order.getDateTime());
        existingOrder.setStatus(order.getStatus());

        orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        var startTime = System.currentTimeMillis();

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ORDER NOT FOUND"));

        orderRepository.delete(existingOrder);

        Timer.measure("[DELETE ORDER] - Sucessfully", startTime);
    }

    private Order getOrderIfExists(long id) {
        Order order = orderRepository.getById(id);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        return order;
    }
}
