package com.example.demo.service.impl;

import com.example.demo.dao.repo.AppleProductsMapRepo;
import com.example.demo.dao.repo.AppleProductsRepo;
import com.example.demo.dto.AppleProductsMapDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AppleProductsMapMapper;
import com.example.demo.service.AppleProductsMapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppleProductsMapServiceImpl implements AppleProductsMapService {
    private final AppleProductsMapMapper mapMapper;
    private final AppleProductsMapRepo mapRepo;

    private final AppleProductsRepo stockRepo;

    @Override
    public AppleProductsMapDto createMap(AppleProductsMapDto dto) {
        stockRepo.findById(dto.getId()).orElseThrow(
                () -> new BadRequestException("SUCH PRODUCT DOESN'T EXIST")
        );
        var entity = mapMapper.mapDtoToEntity(dto);
        entity.setProductName(dto.getProductName());
        mapRepo.save(entity);
        return mapMapper.mapEntityToDto(entity);
    }

    @Override
    public AppleProductsMapDto findMap(Long mapId) {
        return mapMapper.mapEntityToDto(mapRepo.findById(mapId).orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        ));
    }

    @Override
    public AppleProductsMapDto updateMap(Long mapId, AppleProductsMapDto dto) {
        mapRepo.findById(mapId).orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        var entity = mapMapper.mapDtoToEntity(dto);
        entity.setId(mapId);
        entity.setUpdatedDate(LocalDateTime.now());
        return mapMapper.mapEntityToDto(mapRepo.save(entity));
    }

    @Override
    public List<AppleProductsMapDto> findAllMap() {
        if (mapRepo.findAll() == null) throw new NotFoundException("NOT FOUND");
        return mapRepo.findAll().stream().map(mapMapper::mapEntityToDto).collect(Collectors.toList());

    }
}
