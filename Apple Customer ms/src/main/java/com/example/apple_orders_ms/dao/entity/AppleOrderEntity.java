package com.example.apple_orders_ms.dao.entity;

import com.example.apple_orders_ms.enums.DevicesEnum;
import com.example.apple_orders_ms.enums.ModelsEnum;
import com.example.apple_orders_ms.enums.OrderStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "apple_orders")
public class AppleOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DevicesEnum device;

    @Enumerated(EnumType.STRING)
    private ModelsEnum model;

    private String name;

    private String surname;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    private Long customerId;


}


