package com.example.apple_orders_ms.controller;

import com.example.apple_orders_ms.dao.entity.AppleOrderEntity;
import com.example.apple_orders_ms.dto.AppleOrderDto;
import com.example.apple_orders_ms.dto.ResponseDto;
import com.example.apple_orders_ms.enums.DevicesEnum;
import com.example.apple_orders_ms.service.impl.AppleOrdersServiceImpl;
import com.example.apple_orders_ms.util.JwtTokenChecker;
import com.example.apple_orders_ms.util.ResponseCreator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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


@RestController
@RequiredArgsConstructor
@RequestMapping("/apple-orders")
@Tag(name = "Orders Controller", description = "Methods for Apple Products' Orders")
@Slf4j
@Validated
public class ProductOrdersController {
    private final AppleOrdersServiceImpl appService;
    private final JwtTokenChecker jwtTokenChecker;

    @PostMapping("/order-creation")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<AppleOrderDto> createOrder(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestBody AppleOrderDto applicationDto
    ) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Order creation method is called, applicationDtoBody, {}", applicationDto);
        return ResponseCreator.createSuccessResponse(appService.createApplication(applicationDto));
    }

    @GetMapping("/orders-with-pages")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleOrderEntity>> getAllOrders(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size) {
        jwtTokenChecker.validateJwtToken(token);
        Pageable pageable = PageRequest.of(page, size);
        Page<AppleOrderEntity> itemPage = appService.getAllOrders(pageable);
        log.info("Orders consistently search method is called, pageParam: {},sizeParam: {}", page, size);
        return ResponseCreator.createSuccessResponse(itemPage.getContent());
    }

    @GetMapping("/orders-by-name")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleOrderEntity>> getOrdersByName(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size,
            @Valid @RequestParam String name
    ) {
        jwtTokenChecker.validateJwtToken(token);
        Pageable pageable = PageRequest.of(page, size);
        Page<AppleOrderEntity> itemPage = appService.getOrdersByName(name, pageable);
        log.info("Orders search by name method is called, nameParam {}", name);
        return ResponseCreator.createSuccessResponse(itemPage.getContent());
    }

    @GetMapping("/orders-by-surname")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleOrderEntity>> getOrdersBySurName(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size,
            @Valid @RequestParam String surname
    ) {
        jwtTokenChecker.validateJwtToken(token);
        Pageable pageable = PageRequest.of(page, size);
        Page<AppleOrderEntity> itemPage = appService.getOrdersBySurName(surname, pageable);
        log.info("Orders search by surname method is called, surnameParam {}", surname);
        return ResponseCreator.createSuccessResponse(itemPage.getContent());
    }

    @GetMapping("/orders-by-device")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleOrderEntity>> getOrdersByDevice(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size,
            @Valid @RequestParam DevicesEnum device
    ) {
        jwtTokenChecker.validateJwtToken(token);
        Pageable pageable = PageRequest.of(page, size);
        Page<AppleOrderEntity> itemPage = appService.getOrdersByDevice(device, pageable);
        log.info("Orders search by device method is called, deviceParam {}", device);
        return ResponseCreator.createSuccessResponse(itemPage.getContent());
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDto<Void> deleteApplication(
            @RequestHeader(name = "Bearer") String token,
            @Valid @PathVariable Long orderId) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Order delete by id method is called, orderIdParam {}", orderId);
        appService.deleteOrder(orderId);
        return ResponseCreator.createSuccessResponse(null);
    }

    @GetMapping("/order-info/{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<AppleOrderDto> getApplication(
            @RequestHeader(name = "Bearer") String token,
            @Valid @PathVariable Long orderId) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Order search by orderId method is called, orderIdParam {}", orderId);
        return ResponseCreator.createSuccessResponse(appService.getApplication(orderId));
    }

    @PutMapping("/order-info/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto<AppleOrderDto> updateApplication(
            @RequestHeader(name = "Bearer") String token,
            @Valid @RequestBody AppleOrderDto appleAppDto,
            @Valid @PathVariable Long orderId
    ) {
        jwtTokenChecker.validateJwtToken(token);
        log.info("Order modifying method is called, appleDtoBody {}, orderIdParam {}", appleAppDto, orderId);
        return ResponseCreator.createSuccessResponse(appService.updateOrder(appleAppDto, orderId));
    }
}
