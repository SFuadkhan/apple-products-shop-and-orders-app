package com.example.demo.mapper;

import com.example.demo.dao.entity.AppleProductsMapEntity;
import com.example.demo.dto.AppleProductsMapDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AppleProductsMapMapper {

    AppleProductsMapEntity mapDtoToEntity(AppleProductsMapDto dto);

    AppleProductsMapDto mapEntityToDto(AppleProductsMapEntity entity);
}
