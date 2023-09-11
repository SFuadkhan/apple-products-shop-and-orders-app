package com.example.demo.sheduler;

import com.example.demo.dao.entity.AppleProductsEntity;
import com.example.demo.dao.repo.AppleProductsRepo;
import com.example.demo.enums.ProductsStatusEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CheckStockScheduler {
    private final AppleProductsRepo stockRepo;
    @Scheduled(fixedDelay = 1000000)
    @Transactional
    public void checkStock() {
        log.info("scheduled method to check stock is called");
        List<AppleProductsEntity> entities = stockRepo.findByProductsInStockAndProductsStatus(0, ProductsStatusEnum.ACTIVE);
        if (entities.isEmpty()){
            log.info("no stock product is needed to be fixed");
            return;
        }
        entities.forEach(entity -> {
            entity.setProductsStatus(ProductsStatusEnum.INACTIVE);
            entity.setUpdatedAt(LocalDateTime.now());
        });
        log.info("all stock is checked and fixed");
        stockRepo.saveAll(entities);
    }
}
