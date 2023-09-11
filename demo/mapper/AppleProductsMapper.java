package com.example.demo.mapper;

import com.example.demo.dao.entity.AppleProductsEntity;
import com.example.demo.dto.AppleProductsDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AppleProductsMapper {

    AppleProductsDto mapEntityToDto(AppleProductsEntity appleProductsEntity);

    AppleProductsEntity mapDtoToEntity(AppleProductsDto appleProductsDto);
}
