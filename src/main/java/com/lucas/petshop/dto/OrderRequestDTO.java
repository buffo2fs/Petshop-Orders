package com.lucas.petshop.dto;

import com.lucas.petshop.enums.OrderStatus;
import jakarta.validation.constraints.*;


public record OrderRequestDTO (

    @NotNull(message = "QUANTITY FIELD MUST BE INFORMED")
    @Positive(message = "QUANTITY SHOULD BE GREATER THAN 0 (ZERO)")
    Integer totalItemsCount,

    @NotBlank(message = "CLIENTE FILED MUST BE INFORMED")
    @Size(min = 5, max = 255, message ="CLIENT NAME MUST CONTAIN BETWEEN 5 - 255 CHARACTERS")
    String client,

    @NotNull(message = "TOTAL ORDER CANNOT BE EMPTY")
    @Positive(message = "TOTAL ORDER SHOULD BE GREATER THAN 0 (ZERO")
    Integer totalAmount,

    @NotNull(message = "ORDER STATUS IS REQUIRED")
    OrderStatus status

) {}
