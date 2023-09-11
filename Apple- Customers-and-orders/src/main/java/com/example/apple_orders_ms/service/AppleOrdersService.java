package com.example.apple_orders_ms.service;

import com.example.apple_orders_ms.dao.entity.AppleOrderEntity;
import com.example.apple_orders_ms.dto.AppleOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppleOrdersService {

    AppleOrderDto createApplication(AppleOrderDto applicationDto);

    AppleOrderDto getApplication(Long applicationId);

    Page<AppleOrderEntity> getAllOrders(Pageable pageable);

    AppleOrderDto updateOrder(AppleOrderDto appleAppDto, Long applicationId);

    void deleteOrder(Long applicationId);
}
