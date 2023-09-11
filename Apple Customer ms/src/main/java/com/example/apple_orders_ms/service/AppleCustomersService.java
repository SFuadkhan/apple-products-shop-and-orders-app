package com.example.apple_orders_ms.service;

import com.example.apple_orders_ms.dao.entity.AppleCustomersEntity;
import com.example.apple_orders_ms.dto.AppleCustomersDto;
import com.example.apple_orders_ms.dto.AppleOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppleCustomersService {
    AppleCustomersDto createApplication(AppleCustomersDto appleCustomersDto);

    AppleCustomersDto getCustomerById(Long customerId);

    Page<AppleCustomersEntity> getAllCustomers(Pageable pageable);

    Page<AppleCustomersEntity> getCustomerByName(String name, Pageable pageable);

    Page<AppleCustomersEntity> getCustomersBySurName(String surname, Pageable pageable);

    AppleCustomersDto updateCustomerById(AppleCustomersDto appleCustomersDto, Long customerId);

    void deleteCustomer(Long customerId);

    void checkIfCustomerExist(AppleOrderDto appleOrderDto);

    void ifNotExistSave(AppleOrderDto appleOrderDto);
}
