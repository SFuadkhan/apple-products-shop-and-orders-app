package com.example.apple_orders_ms.mapper;

import com.example.apple_orders_ms.dao.entity.AppleCustomersEntity;
import com.example.apple_orders_ms.dto.AppleCustomersDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AppleCustomersMapper {
    AppleCustomersDto mapEntityToDto(AppleCustomersEntity appleCustomersEntity);

    AppleCustomersEntity mapDtoToEntity(AppleCustomersDto appleCustomersDto);

}
