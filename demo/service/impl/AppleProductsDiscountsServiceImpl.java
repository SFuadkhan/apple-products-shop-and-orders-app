package com.example.demo.service.impl;

import com.example.demo.dao.repo.AppleProductsDiscountsRepo;
import com.example.demo.dao.repo.AppleProductsRepo;
import com.example.demo.dto.AppleProductsDiscountsDto;
import com.example.demo.enums.ProductsStatusEnum;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AppleProductsDiscountMapper;
import com.example.demo.service.AppleProductsDiscountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppleProductsDiscountsServiceImpl implements AppleProductsDiscountsService {
    private final AppleProductsDiscountsRepo discountsRepo;
    private final AppleProductsDiscountMapper discountMapper;
    private final AppleProductsRepo stockRepo;

    @Override
    public AppleProductsDiscountsDto createDiscount(AppleProductsDiscountsDto dto) {
        log.warn("if dto has not valid product id, it will get exception");
        stockRepo.findById(dto.getProductId()).orElseThrow(
                () -> new BadRequestException("SUCH PRODUCT DOESN'T EXIST")
        );
        var entity = discountMapper.mapDtoToEntity(dto);
        discountsRepo.save(entity);
        return discountMapper.mapEntityToDto(entity);
    }

    @Override
    public AppleProductsDiscountsDto findDiscount(Long discountId) {
        log.warn("if provided id doesn't exist, it will get exception {}", discountId);
        return discountMapper.mapEntityToDto(discountsRepo.findById(discountId).orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        ));
    }

    @Override
    public AppleProductsDiscountsDto updateDiscount(Long discountId, AppleProductsDiscountsDto dto) {
        log.warn("if provided id doesn't exist, it will get exception, {}", discountId);
        discountsRepo.findById(discountId).orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        log.warn("if dto isn't valid it will get an exception, {}", dto);
        var entity = discountMapper.mapDtoToEntity(dto);
        entity.setDiscountId(discountId);
        return discountMapper.mapEntityToDto(discountsRepo.save(entity));
    }

    @Override
    public List<AppleProductsDiscountsDto> findAllDiscounts() {
        log.warn("if there is no discount , it will get an exception");
        if (discountsRepo.findAll().isEmpty()) throw new NotFoundException("NOT FOUND");
        return discountsRepo.findAll().stream().map(discountMapper::mapEntityToDto).collect(Collectors.toList());

    }

    @Override
    public List<AppleProductsDiscountsDto> findActiveDiscounts() {
        log.warn("if there is no discount , it will get an exception");
        if (discountsRepo.findByDiscountStatus(ProductsStatusEnum.ACTIVE).isEmpty())
            throw new NotFoundException("NOT FOUND");
        return discountsRepo.findByDiscountStatus(ProductsStatusEnum.ACTIVE).
                stream().map(discountMapper::mapEntityToDto).collect(Collectors.toList());

    }

    @Override
    public List<AppleProductsDiscountsDto> findInActiveDiscounts() {
        log.warn("if there is no discount , it will get an exception");
        if (discountsRepo.findByDiscountStatus(ProductsStatusEnum.INACTIVE).isEmpty())
            throw new NotFoundException("NOT FOUND");
        return discountsRepo.findByDiscountStatus(ProductsStatusEnum.INACTIVE).
                stream().map(discountMapper::mapEntityToDto).collect(Collectors.toList());

    }

    @Override
    public Boolean checkIfThereIsDiscount(Long productId) {
        log.info("true means - there exists an active discount, otherwise doesn't");
        return discountsRepo.findByProductIdAndDiscountStatus(productId, ProductsStatusEnum.ACTIVE).isPresent();
    }

    @Override
    public Integer discountCalculus(Integer percentage, Integer price) {
        log.info("method to calculate price,{}, with discount, {} , is called", price, percentage);
        return price * (100 - percentage) / 100;
    }

    @Override
    public Integer getDiscountPercentage(Long productId) {
        log.info("method to get discount percentage is called");
        return discountsRepo.findByProductId(productId).getDiscountPercentage();
    }
}
