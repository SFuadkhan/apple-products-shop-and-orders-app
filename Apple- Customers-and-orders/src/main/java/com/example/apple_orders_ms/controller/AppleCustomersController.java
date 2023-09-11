package com.example.apple_orders_ms.controller;

import com.example.apple_orders_ms.dao.entity.AppleCustomersEntity;
import com.example.apple_orders_ms.dto.AppleCustomersDto;
import com.example.apple_orders_ms.dto.ResponseDto;
import com.example.apple_orders_ms.service.impl.AppleCustomersServiceImpl;
import com.example.apple_orders_ms.util.JwtTokenChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.apple_orders_ms.util.ResponseCreator.createSuccessResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apple-customers")
@Tag(name = "Customers Controller", description = "Methods for Apple Customers")
@Slf4j
@Validated
public class AppleCustomersController {
    private final AppleCustomersServiceImpl customersService;
    private final JwtTokenChecker jwtTokenChecker;

    @PostMapping("/creation")
    @ResponseStatus(CREATED)
    public ResponseDto<AppleCustomersDto> createCustomer(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestBody AppleCustomersDto requestBody) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Customer creation method is called, applicationDtoBody: {}", requestBody);
        return createSuccessResponse(customersService.createApplication(requestBody));
    }

    @GetMapping("/info-with-pages")
    public ResponseDto<List<AppleCustomersEntity>> getAllCustomers(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size
    ) {
        jwtTokenChecker.validateJwtToken(token);
        Pageable pageable = PageRequest.of(page, size);
        Page<AppleCustomersEntity> itemPage = customersService.getAllCustomers(pageable);
        log.info("Customers consistently search method is called, pageParam: {}, sizeParam: {}", page, size);
        return createSuccessResponse(itemPage.getContent());
    }

    @GetMapping("/customers-by-name")
    public ResponseDto<List<AppleCustomersEntity>> getCustomersByName(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size,
            @Valid @RequestParam String name
    ) {
        jwtTokenChecker.validateJwtToken(token);
        Pageable pageable = PageRequest.of(page, size);
        Page<AppleCustomersEntity> itemPage = customersService.getCustomerByName(name, pageable);
        log.info("Customers search by name method is called, nameParam {}", name);
        return createSuccessResponse(itemPage.getContent());
    }

    @GetMapping("/customers-by-surname")
    public ResponseDto<List<AppleCustomersEntity>> getCustomersBySurName(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size,
            @Valid @RequestParam String surname
    ) {
        jwtTokenChecker.validateJwtToken(token);
        Pageable pageable = PageRequest.of(page, size);
        Page<AppleCustomersEntity> itemPage = customersService.getCustomersBySurName(surname, pageable);
        log.info("Customers search by surname method is called, surnameParam: {}", surname);
        return createSuccessResponse(itemPage.getContent());
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(NO_CONTENT)
    public ResponseDto<Void> deleteCustomer(
            @RequestHeader(name = "Bearer") String token,
            @Valid @PathVariable Long customerId
    ) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Customer delete by id method is called, orderIdParam {}", customerId);
        customersService.deleteCustomer(customerId);
        return createSuccessResponse(null);
    }

    @GetMapping("/customer-info/{customerId}")
    public ResponseDto<AppleCustomersDto> getCustomerById(
            @RequestHeader(name = "Bearer") String token,
            @Valid @PathVariable Long customerId
    ) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Customer search by orderId method is called, orderIdParam {}", customerId);
        return createSuccessResponse(customersService.getCustomerById(customerId));
    }

    @PutMapping("/customer-info/{customerId}")
    public ResponseDto<AppleCustomersDto> updateCustomerById(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestBody AppleCustomersDto appleCustomersDto,
            @Valid @PathVariable Long customerId
    ) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Customer modifying method is called, appleDtoBody {}, orderIdParam {}", appleCustomersDto, customerId);
        return createSuccessResponse(customersService.updateCustomerById(appleCustomersDto, customerId));
    }

}
