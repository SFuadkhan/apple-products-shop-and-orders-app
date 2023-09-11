package com.example.demo.mapper;

import com.example.demo.dao.entity.AppleProductsDiscountsEntity;
import com.example.demo.dto.AppleProductsDiscountsDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AppleProductsDiscountMapper {

    AppleProductsDiscountsEntity mapDtoToEntity(AppleProductsDiscountsDto dto);

    AppleProductsDiscountsDto mapEntityToDto(AppleProductsDiscountsEntity entity);
}
