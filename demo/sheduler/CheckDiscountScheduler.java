package com.example.demo.sheduler;

import com.example.demo.dao.entity.AppleProductsDiscountsEntity;
import com.example.demo.dao.repo.AppleProductsDiscountsRepo;
import com.example.demo.enums.ProductsStatusEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CheckDiscountScheduler {
    private final AppleProductsDiscountsRepo discountsRepo;
    @Scheduled(fixedDelay = 1000000)
    @Transactional
    public void checkDiscount() {
        log.info("scheduled method to check discounts is called");
        List<AppleProductsDiscountsEntity> entities1 = discountsRepo.findByDiscountStatus(ProductsStatusEnum.ACTIVE);

        List<AppleProductsDiscountsEntity> entities2 = discountsRepo.findByDiscountStatus(ProductsStatusEnum.INACTIVE);

        entities1.forEach(entity -> {
            if (Objects.equals(entity.getEndDate(), LocalDate.now()))
                entity.setDiscountStatus(ProductsStatusEnum.INACTIVE);

        });
        entities2.forEach(entity -> {
            if (Objects.equals(entity.getStartDate(), LocalDate.now()))
                entity.setDiscountStatus(ProductsStatusEnum.ACTIVE);
        });
        discountsRepo.saveAll(entities1);
        discountsRepo.saveAll(entities2);
        log.info("all discounts are checked and fixed if needed");
    }
}
