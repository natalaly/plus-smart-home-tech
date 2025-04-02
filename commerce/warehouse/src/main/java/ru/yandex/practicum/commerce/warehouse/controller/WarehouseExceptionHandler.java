package ru.yandex.practicum.commerce.warehouse.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.exception.handler.GlobalExceptionHandler;

@RestControllerAdvice
public class WarehouseExceptionHandler extends GlobalExceptionHandler {

}
