package com.example.apple_orders_ms.scheduler;

import com.example.apple_orders_ms.dao.entity.AppleCustomersEntity;
import com.example.apple_orders_ms.dao.repo.AppleAppRepo;
import com.example.apple_orders_ms.dao.repo.AppleCustomersRepo;
import com.example.apple_orders_ms.enums.OrderStatusEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class CheckCustomersOrdersNumber {
    private final AppleAppRepo applicationRepository;
    private final AppleCustomersRepo appleCustomersRepo;
    @Scheduled(fixedDelay = 1000000)
    @Transactional
    public void performCustomerOrdersCounting() {
        log.info("this method updates the number of orders of each customer IRT");
        List<AppleCustomersEntity> appleCustomersEntities = appleCustomersRepo.findAll();
        appleCustomersEntities.forEach(
                entity -> {
                    long orderEntities = applicationRepository
                            .findByCustomerIdAndOrderStatus(entity.getId(), OrderStatusEnum.IN_PROGRESS).size();
                    entity.setOrders(orderEntities);
                }
        );
        appleCustomersRepo.saveAll(appleCustomersEntities);
    }
}
