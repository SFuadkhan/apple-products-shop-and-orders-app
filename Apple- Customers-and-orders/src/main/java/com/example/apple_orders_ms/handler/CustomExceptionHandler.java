package com.example.apple_orders_ms.handler;

import com.example.apple_orders_ms.dto.ExceptionDto;
import com.example.apple_orders_ms.exception.BadRequestException;
import com.example.apple_orders_ms.exception.ForbiddenException;
import com.example.apple_orders_ms.exception.NotFoundException;
import com.example.apple_orders_ms.exception.UnauthorizedException;
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
        log.warn("BadRequestException: ", ex.getCause());
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException ex) {
        log.warn("NotFoundException: ", ex.getCause());
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ResponseEntity<ExceptionDto> handleForbiddenException(ForbiddenException ex) {
        log.warn("ForbiddenException: ", ex.getCause());
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.FORBIDDEN.value(), ex.getMessage()),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private ResponseEntity<ExceptionDto> handleUnauthorizedException(UnauthorizedException ex) {
        log.warn("UnauthorizedException: ", ex.getCause());
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }
//    @ResponseStatus(BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ResponseDto<Object>> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException ex) {
//        log.error(constMsg, ex.getMessage());
//        var message = ex.getBindingResult().getAllErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(joining(", "));
//        return new ResponseEntity<>(new ResponseDto<>(FAILURE.getStatusCode(), message,
//                request.getRequestURI(), now(), null), BAD_REQUEST);
//    }

}
