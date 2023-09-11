package com.example.apple_orders_ms.service.impl;

import com.example.apple_orders_ms.dao.entity.AppleCustomersEntity;
import com.example.apple_orders_ms.dao.repo.AppleCustomersRepo;
import com.example.apple_orders_ms.dto.AppleCustomersDto;
import com.example.apple_orders_ms.dto.AppleOrderDto;
import com.example.apple_orders_ms.exception.BadRequestException;
import com.example.apple_orders_ms.exception.NotFoundException;
import com.example.apple_orders_ms.mapper.AppleCustomersMapper;
import com.example.apple_orders_ms.service.AppleCustomersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class AppleCustomersServiceImpl implements AppleCustomersService {
    private final AppleCustomersRepo appleCustomersRepository;
    private final AppleCustomersMapper mapper;
    private final AppleCustomersDto appleCustomersDto;


    public AppleCustomersDto createApplication(AppleCustomersDto appleCustomersDto) {
        if (Objects.equals(appleCustomersDto.getSurname(), "string") || Objects.equals(appleCustomersDto.getName(), "string"))
            throw new BadRequestException("BAD CREDENTIALS");
        log.info("Provided data is valid");
        var entity = this.mapper.mapDtoToEntity(appleCustomersDto);
        entity.setId(appleCustomersDto.getId());
        var savedEntity = appleCustomersRepository.save(entity);
        return this.mapper.mapEntityToDto(savedEntity);
    }

    public AppleCustomersDto getCustomerById(Long customerId) {
        log.warn("If there is no such id, then it will get ex");
        var entity = appleCustomersRepository.findById(customerId).
                orElseThrow(() -> new NotFoundException("NOT FOUND"));
        log.info("There exists such id");
        return this.mapper.mapEntityToDto(entity);
    }

    public Page<AppleCustomersEntity> getAllCustomers(Pageable pageable) {
        log.warn("If there are no such customers, then it will get ex");
        appleCustomersRepository.findAll(pageable).stream().findAny().orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        log.info("There exist orders");
        return appleCustomersRepository.findAll(pageable);
    }

    public Page<AppleCustomersEntity> getCustomerByName(String name, Pageable pageable) {
        log.warn("If there are no such customers with request name, then it will get ex");
        appleCustomersRepository.findByName(name, pageable).stream().findAny().orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        log.info("There exist suitable customers");
        return appleCustomersRepository.findByName(name, pageable);
    }

    public Page<AppleCustomersEntity> getCustomersBySurName(String surname, Pageable pageable) {
        log.warn("If there are no customers with request surname , then it will get ex");
        appleCustomersRepository.findBySurname(surname, pageable).stream().findAny().orElseThrow(
                () -> new NotFoundException("NOT FOUND")
        );
        log.info("There exist suitable customers");
        return appleCustomersRepository.findBySurname(surname, pageable);
    }


    public AppleCustomersDto updateCustomerById(AppleCustomersDto appleCustomersDto, Long customerId) {
        log.warn("If there is no such id, it will get ex");
        appleCustomersRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(("NOT FOUND")));
        log.warn("If provided data is invalid it will get ex");
        var updatedEntity = this.mapper.mapDtoToEntity(appleCustomersDto);
        updatedEntity.setId(customerId);
        log.info("Updated data is saved");
        return this.mapper.mapEntityToDto(appleCustomersRepository.save(updatedEntity));
    }

    public void deleteCustomer(Long customerId) {
        log.warn("If there is no such id, it will get ex");
        var entity = appleCustomersRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("NOT FOUND"));
        log.info("Data is deleted");
        appleCustomersRepository.delete(entity);
    }

    public void checkIfCustomerExist(AppleOrderDto appleOrderDto) {
        log.info("checking, if such customer exist, if not he will be added");
        if (appleCustomersRepository.findById(appleOrderDto.getCustomerId()).isEmpty()) {
            ifNotExistSave(appleOrderDto);
            return;
        }
        log.warn("if there will be some code mistake it will get exception");
        var customer = appleCustomersRepository.findById(appleOrderDto.getCustomerId())
                .orElseThrow(() -> new BadRequestException("SMT WRONG"));
        log.info("incrementing orders and purchases with every post mapping");
        appleCustomersRepository.deleteById(appleOrderDto.getCustomerId());
        customer.setOrders(customer.getOrders() + 1);
        customer.setPurchases(customer.getPurchases() + 1);
        appleCustomersRepository.save(customer);
        log.info("method is over,customer is created or found successfully");
    }

    public void ifNotExistSave(AppleOrderDto appleOrderDto) {
        log.info("putting data from appleOrderDto, {}", appleOrderDto + "as customer info");
        appleCustomersDto.setName(appleOrderDto.getName());
        appleCustomersDto.setSurname(appleOrderDto.getSurname());
        appleCustomersDto.setOrders(1L);
        appleCustomersDto.setPurchases(1L);
        appleCustomersDto.setId(appleOrderDto.getId());
        appleCustomersRepository.save(mapper.mapDtoToEntity(appleCustomersDto));
        log.info("customer is created successfully");
    }

}
