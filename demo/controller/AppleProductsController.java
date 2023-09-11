package com.example.demo.controller;

import com.example.demo.dto.AppleProductsDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.AppleProductsService;
import com.example.demo.util.ResponseCreator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apple-products")
@Tag(name = "Apple Products Controller", description = "Methods for Apple products")
@Validated
@Slf4j
public class AppleProductsController {
    private final AppleProductsService service;

    @PostMapping("/create-stock")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<AppleProductsDto> createStock(
            @Valid @RequestBody AppleProductsDto appleProductsDto
    ) {
        log.info("method for creating stock is called,{}",appleProductsDto);
        return ResponseCreator.createSuccessResponse(service.createStock(appleProductsDto));
    }

    @GetMapping("/find-stock")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<AppleProductsDto> findStock(
            @Valid @RequestParam Long stockId
    ) {
        log.info("method for search a stock is called,{}",stockId);
        return ResponseCreator.createSuccessResponse(service.findStock(stockId));
    }

    @PutMapping("/modify-stock")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto<AppleProductsDto> updateStock(
            @Valid @RequestParam Long stockId,
            @Valid @RequestBody AppleProductsDto dto
    ) {
        log.info("method to modify stock is called {}, {}",stockId,dto);
        return ResponseCreator.createSuccessResponse(service.updateStock(stockId, dto));
    }

    @GetMapping("/find-all-stock")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleProductsDto>> findAllStock() {
        log.info("method to find all stock is called");
        return ResponseCreator.createSuccessResponse(service.findAllStock());
    }

    @PatchMapping("/add-stock")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto<AppleProductsDto> addStock(
            @Valid @RequestParam Long id,
            @Valid @RequestParam Integer amount
    ) {
        log.info("method to add amount to stock is called {},{}",id,amount);
        return ResponseCreator.createSuccessResponse(service.addProductsInStock(id, amount));
    }

    @GetMapping("/find-active-stock")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleProductsDto>> findAllActiveStock() {
        log.info("method to find all active stock is called");
        return ResponseCreator.createSuccessResponse(service.findAllActiveStock());
    }

    @GetMapping("/find-inactive-stock")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleProductsDto>> findAllInactiveStock() {
        log.info("method to find all inactive stock is called");
        return ResponseCreator.createSuccessResponse(service.findAllInactiveStock());
    }

}
