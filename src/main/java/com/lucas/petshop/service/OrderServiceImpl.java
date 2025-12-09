package com.lucas.petshop.service;


import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.enums.OrderStatus;
import com.lucas.petshop.exception.OrderStatusInvalid;
import com.lucas.petshop.mapper.OrderMapper;
import com.lucas.petshop.repository.OrderRepository;
import com.lucas.petshop.model.Order;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        var startTime = System.currentTimeMillis();
        Timer.measure("[GET ALL ORDERS] - Successfully", startTime);

        return orderRepository.findAll()
                .stream()
                .filter(order -> !Boolean.TRUE.equals(order.getDeletedOrder()))
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(Long id){
        var startTime = System.currentTimeMillis();

        Order order = getOrderIfExists(id);

        if (Boolean.TRUE.equals(order.getDeletedOrder())) {
            throw new RuntimeException("PRODUCT IS DELETED");
        }

        Timer.measure("[GET ORDER BY ID] - Successfully", startTime);
        return OrderMapper.toDTO(order);

    }

    @Override
    @Transactional
    public long createOrder(OrderRequestDTO dto){
        var startTime = System.currentTimeMillis();

        if(dto.status() == null) {
            throw new OrderStatusInvalid("STATUS IS REQUIRED");

        }
        Order orders = OrderMapper.toEntity(dto);
        Order saved = orderRepository.save(orders);

        Timer.measure("[CREATE ORDER] - Successfully", startTime);

        return saved.getId();
    }

    @Override
    @Transactional
    public void updateOrder(Long id, OrderRequestDTO dto) {
        var startTime = System.currentTimeMillis();

        Order existing = getOrderIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedOrder())){
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        existing.setTotalItemsCount(dto.totalItemsCount());
        existing.setClient(dto.client());
        existing.setTotalAmount(dto.totalAmount());
        existing.setStatus(dto.status());
        existing.setOrderUpdate(LocalDateTime.now());

        if(dto.status() == OrderStatus.CANCELED){
            existing.setDeletedOrder(true);
        }

        orderRepository.save(existing);

        Timer.measure("[UPDATE ORDER] - Successfully", startTime);
    }

    @Override
    public void deleteOrder(Long id) {
        var startTime = System.currentTimeMillis();

        Order existing = getOrderIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedOrder())){
            throw new RuntimeException("ORDER ALREADY DELETED");
        }

        existing.setDeletedOrder(true);
        existing.setOrderUpdate(LocalDateTime.now());

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
