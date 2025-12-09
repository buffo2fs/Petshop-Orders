package com.lucas.petshop.exception;

public class OrderStatusInvalid extends RuntimeException {
    public OrderStatusInvalid(String message){
        super(message);
    }
}
