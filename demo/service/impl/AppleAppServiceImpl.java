package com.example.demo.service.impl;

import com.example.demo.dao.repo.AppleProductsMapRepo;
import com.example.demo.dao.repo.AppleProductsRepo;
import com.example.demo.dto.AppleProductsDto;
import com.example.demo.enums.ProductsModelsEnum;
import com.example.demo.enums.ProductsStatusEnum;
import com.example.demo.enums.ProductsTypesEnum;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AppleProductsMapper;
import com.example.demo.service.AppleAppService;
import com.example.demo.service.AppleProductsDiscountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableScheduling
@Slf4j
public class AppleAppServiceImpl implements AppleAppService {
    private final AppleProductsRepo stockRepo;
    private final AppleProductsMapRepo mapRepo;
    private final AppleProductsDiscountsService discountsService;
    private final AppleProductsMapper productsMapper;

    @Override
    public Boolean isProductActive(ProductsTypesEnum typesEnum, ProductsModelsEnum modelsEnum) {
        log.info("");
        Long entityId = mapRepo.findByProductTypeAndProductModel(typesEnum, modelsEnum).getId();
        if (entityId == null) throw new NotFoundException("PRODUCT ISN'T FOUND");
        var entity = stockRepo.findById(entityId).orElseThrow(() -> new NotFoundException("NOT FOUND"));
        return entity.getProductsStatus() == ProductsStatusEnum.ACTIVE;
    }

    @Override
    public AppleProductsDto findStockWithActualPrice(Long productId) {
        var entity = stockRepo.findById(productId).orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        if (discountsService.checkIfThereIsDiscount(productId)) {
            Integer newPrice = discountsService.discountCalculus(discountsService.getDiscountPercentage(productId),
                    entity.getPrice());
            entity.setPrice(newPrice);
        }
        return productsMapper.mapEntityToDto(entity);
    }

}
