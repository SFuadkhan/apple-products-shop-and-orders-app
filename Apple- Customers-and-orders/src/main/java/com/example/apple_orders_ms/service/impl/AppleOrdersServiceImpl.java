package com.example.apple_orders_ms.service.impl;

import com.example.apple_orders_ms.dao.entity.AppleOrderEntity;
import com.example.apple_orders_ms.dao.repo.AppleAppRepo;
import com.example.apple_orders_ms.dto.AppleOrderDto;
import com.example.apple_orders_ms.enums.DevicesEnum;
import com.example.apple_orders_ms.enums.ModelsEnum;
import com.example.apple_orders_ms.exception.BadRequestException;
import com.example.apple_orders_ms.exception.NotFoundException;
import com.example.apple_orders_ms.mapper.AppleAppMapper;
import com.example.apple_orders_ms.service.AppleOrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppleOrdersServiceImpl implements AppleOrdersService {

    private final AppleAppRepo applicationRepository;
    private final AppleAppMapper mapper;
    private final AppleCustomersServiceImpl appleCustomersService;

    public AppleOrderDto createApplication(AppleOrderDto applicationDto) {
        log.warn("User request will get exception if it is invalid");
        if (Objects.equals(applicationDto.getName(), "string") || Objects.equals(applicationDto.getSurname(), "string")) {
            throw new BadRequestException("BAD CREDENTIALS, PLEASE INPUT NAME AND SURNAME");
        } else if (applicationDto.getDevice() == DevicesEnum.AppleWatch && (applicationDto.getModel() != ModelsEnum.Ultra && applicationDto.getModel() != ModelsEnum.Series && applicationDto.getModel() != ModelsEnum.SE && applicationDto.getModel() != ModelsEnum.Nike)) {
            throw new BadRequestException("BAD CREDENTIALS, PLEASE INPUT VALID MODEL AND DEVICE");

        } else if (applicationDto.getDevice() == DevicesEnum.Macbook && (applicationDto.getModel() != ModelsEnum.Air && applicationDto.getModel() != ModelsEnum.Pro && applicationDto.getModel() != ModelsEnum.Mini && applicationDto.getModel() != ModelsEnum.Imac24)) {
            throw new BadRequestException("BAD CREDENTIALS, PLEASE INPUT VALID MODEL AND DEVICE");

        } else if (applicationDto.getDevice() == DevicesEnum.Iphone && (applicationDto.getModel() != ModelsEnum.Iphone11 && applicationDto.getModel() != ModelsEnum.Iphone12 && applicationDto.getModel() != ModelsEnum.Iphone13 && applicationDto.getModel() != ModelsEnum.Iphone14)) {
            throw new BadRequestException("BAD CREDENTIALS, PLEASE INPUT VALID MODEL AND DEVICE");

        } else if (applicationDto.getDevice() == DevicesEnum.Ipad && (applicationDto.getModel() != ModelsEnum.Air5thGen && applicationDto.getModel() != ModelsEnum.Mini6thGen && applicationDto.getModel() != ModelsEnum.Ipad9thGen && applicationDto.getModel() != ModelsEnum.Ipad10thGen)) {
            throw new BadRequestException("BAD CREDENTIALS, PLEASE INPUT VALID MODEL AND DEVICE");

        } else if (applicationDto.getDevice() == DevicesEnum.Airpods && (applicationDto.getModel() != ModelsEnum.SecondGen && applicationDto.getModel() != ModelsEnum.ThirdGen && applicationDto.getModel() != ModelsEnum.ProSecondGen && applicationDto.getModel() != ModelsEnum.FirstGen)) {
            throw new BadRequestException("BAD CREDENTIALS, PLEASE INPUT VALID MODEL AND DEVICE");

        } else if (applicationDto.getDevice() == DevicesEnum.AppleTv && (applicationDto.getModel() != ModelsEnum.TvHD && applicationDto.getModel() != ModelsEnum.Tv4K1stGen && applicationDto.getModel() != ModelsEnum.Tv4K2ndGen && applicationDto.getModel() != ModelsEnum.Tv4K3rdGen)) {
            throw new BadRequestException("BAD CREDENTIALS, PLEASE INPUT VALID MODEL AND DEVICE");
        }

        log.info("Provided data is valid");
        var entity = this.mapper.mapDtoToEntity(applicationDto);
        entity.setCustomerId(applicationDto.getCustomerId());
        var savedEntity = applicationRepository.save(entity);
        log.info("Checking if such customer exist,if not creating");
//        appleCustomersService.checkIfCustomerExist(applicationDto);
        return this.mapper.mapEntityToDto(savedEntity);
    }

    public AppleOrderDto getApplication(Long applicationId) {
        log.warn("If there is no such id, then it will get ex");
        var entity = applicationRepository.findById(applicationId).orElseThrow(() -> new NotFoundException("NOT FOUND"));
        log.info("There exists such id");
        return this.mapper.mapEntityToDto(entity);
    }

    public Page<AppleOrderEntity> getAllOrders(Pageable pageable) {
        log.warn("If there are no orders, then it will get ex");
        applicationRepository.findAll(pageable).stream().findAny().orElseThrow(() -> new NotFoundException("NOT FOUND"));
        log.info("There exist orders");
        return applicationRepository.findAll(pageable);
    }

    public Page<AppleOrderEntity> getOrdersByName(String name, Pageable pageable) {
        log.warn("If there are no orders with request name, then it will get ex");
        applicationRepository.findByName(name, pageable).stream().findAny().orElseThrow(() -> new NotFoundException("NOT FOUND"));
        log.info("There exist suitable orders");
        return applicationRepository.findByName(name, pageable);
    }

    public Page<AppleOrderEntity> getOrdersBySurName(String surname, Pageable pageable) {
        log.warn("If there are no orders with request surname , then it will get ex");
        applicationRepository.findBySurname(surname, pageable).stream().findAny().orElseThrow(() -> new NotFoundException("NOT FOUND"));
        log.info("There exist suitable orders");
        return applicationRepository.findBySurname(surname, pageable);
    }

    public Page<AppleOrderEntity> getOrdersByDevice(DevicesEnum device, Pageable pageable) {
        log.warn("If there are no orders with request device , then it will get ex");
        applicationRepository.findByDevice(device, pageable).stream().findAny().orElseThrow(() -> new NotFoundException("NOT FOUND"));
        return applicationRepository.findByDevice(device, pageable);
    }


    public AppleOrderDto updateOrder(AppleOrderDto appleAppDto, Long orderId) {
        log.warn("If there is no such id, it will get ex");
        applicationRepository.findById(orderId).orElseThrow(() -> new NotFoundException(("NOT FOUND")));
        log.warn("If provided data is invalid it will get ex");
        var updatedEntity = this.mapper.mapDtoToEntity(appleAppDto);
        updatedEntity.setId(orderId);
        updatedEntity.setUpdatedAt(LocalDateTime.now());
        log.info("Updated data is saved");
        return this.mapper.mapEntityToDto(applicationRepository.save(updatedEntity));
    }

    public void deleteOrder(Long orderId) {
        log.warn("If there is no such id, it will get ex");
        var entity = applicationRepository.findById(orderId).orElseThrow(() -> new NotFoundException("NOT FOUND"));
        log.info("Data is deleted");
        applicationRepository.delete(entity);
    }






}
