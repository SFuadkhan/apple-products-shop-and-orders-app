package com.example.apple_orders_ms.dao.repo;

import com.example.apple_orders_ms.dao.entity.AppleOrderEntity;
import com.example.apple_orders_ms.enums.DevicesEnum;
import com.example.apple_orders_ms.enums.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppleAppRepo extends JpaRepository<AppleOrderEntity,Long> {

    List<AppleOrderEntity> findByCustomerIdAndOrderStatus(Long id, OrderStatusEnum orderStatusEnum);

    Page<AppleOrderEntity> findByName(String name, Pageable pageable);

    Page<AppleOrderEntity> findBySurname(String surname,Pageable pageable);

    Page<AppleOrderEntity> findByDevice(DevicesEnum device, Pageable pageable);
}
