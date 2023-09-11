package com.example.demo.service.impl;


import com.example.demo.dao.entity.AppleProductsEntity;
import com.example.demo.dao.repo.AppleProductsRepo;
import com.example.demo.dto.AppleProductsDto;
import com.example.demo.enums.ProductsStatusEnum;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AppleProductsMapper;
import com.example.demo.service.AppleProductsDiscountsService;
import com.example.demo.service.AppleProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppleProductsServiceImpl implements AppleProductsService {
    private final AppleProductsRepo stockRepo;
    private final AppleProductsMapper productsMapper;
    private final AppleProductsDiscountsService discountsService;

    @Override
    public AppleProductsDto createStock(AppleProductsDto appleProductsDto) {
        var entity = productsMapper.mapDtoToEntity(appleProductsDto);
        entity.setProductsStatus(ProductsStatusEnum.ACTIVE);
        stockRepo.save(entity);
        return productsMapper.mapEntityToDto(entity);
    }

    @Override
    public AppleProductsDto findStock(Long productId) {
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

    @Override
    public AppleProductsDto updateStock(Long productId, AppleProductsDto dto) {
        stockRepo.findById(productId).orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        var entity = productsMapper.mapDtoToEntity(dto);
        entity.setId(productId);
        entity.setProductsStatus(ProductsStatusEnum.ACTIVE);
        entity.setUpdatedAt(LocalDateTime.now());
        return productsMapper.mapEntityToDto(stockRepo.save(entity));
    }

    @Override
    public List<AppleProductsDto> findAllStock() {
        var entities = stockRepo.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("NOT FOUND");
        }
        return entities.stream().map(productsMapper::mapEntityToDto).collect(Collectors.toList());

    }

    @Override
    public List<AppleProductsDto> findAllActiveStock() {
        if (stockRepo.findByProductsStatus(ProductsStatusEnum.ACTIVE) == null)
            throw new NotFoundException("NOT FOUND");
        return stockRepo.findByProductsStatus(ProductsStatusEnum.ACTIVE).
                stream().map(productsMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppleProductsDto> findAllInactiveStock() {
        if (stockRepo.findByProductsStatus(ProductsStatusEnum.INACTIVE) == null)
            throw new NotFoundException("NOT FOUND");
        return stockRepo.findByProductsStatus(ProductsStatusEnum.INACTIVE).
                stream().map(productsMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public AppleProductsDto addProductsInStock(Long id, Integer amount) {
        if (amount <= 0) throw new BadRequestException("INVALID AMOUNT");
        AppleProductsEntity entity;
        entity = stockRepo.findById(id).orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        entity.setProductsInStock(entity.getProductsInStock() + amount);
        entity.setProductsStatus(ProductsStatusEnum.ACTIVE);
        return productsMapper.mapEntityToDto(stockRepo.save(entity));
    }
}
