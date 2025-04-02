package ru.yandex.practicum.commerce.cart.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.exception.handler.GlobalExceptionHandler;

@RestControllerAdvice
public class ShoppingCartExceptionHandler extends GlobalExceptionHandler {

}
