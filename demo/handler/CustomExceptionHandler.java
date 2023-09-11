package com.example.demo.handler;


import com.example.demo.dto.ExceptionDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ExceptionDto> handleBadRequestException(BadRequestException ex) {
        log.warn("BadRequestException: ", ex);
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ExceptionDto handleNotFoundException(NotFoundException ex) {
        log.warn("NotFoundException: ", ex.getCause());
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
