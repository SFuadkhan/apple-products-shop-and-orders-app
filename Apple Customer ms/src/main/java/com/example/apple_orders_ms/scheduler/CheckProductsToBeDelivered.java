package com.example.apple_orders_ms.scheduler;

import com.example.apple_orders_ms.dao.entity.AppleOrderEntity;
import com.example.apple_orders_ms.dao.repo.AppleAppRepo;
import com.example.apple_orders_ms.enums.OrderStatusEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CheckProductsToBeDelivered {
    private final AppleAppRepo applicationRepository;
    @Scheduled(fixedDelay = 1000000)
    @Transactional
    public void performProductsDeliveringOperations() {
        log.info("scheduled method to check if order is delivered is started");
        LocalDate localDate = LocalDate.now();

        List<AppleOrderEntity> entitiesToDelete = applicationRepository.findAll();
        var entitiesToDelete1 = entitiesToDelete.stream().filter(
                entity -> {
                    LocalDate createdAtDate = entity.getCreatedAt().toLocalDate();
                    return Period.between(createdAtDate, localDate).getDays() >= 14;
                }
        ).collect(Collectors.toList());

        entitiesToDelete1.forEach(
                entity -> {
                    entity.setOrderStatus(OrderStatusEnum.DELIVERED);
                    entity.setUpdatedAt(LocalDateTime.now());
                }
        );
        applicationRepository.saveAll(entitiesToDelete1);
    }
}
