package com.example.apple_orders_ms.mapper;

import com.example.apple_orders_ms.dao.entity.AppleOrderEntity;
import com.example.apple_orders_ms.dto.AppleOrderDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AppleAppMapper {

    AppleOrderDto mapEntityToDto(AppleOrderEntity appleAppEntity);

    AppleOrderEntity mapDtoToEntity(AppleOrderDto appleAppDto);

}
