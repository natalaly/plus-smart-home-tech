package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends ApiException {

  public ProductNotFoundException(final String logDetails) {
    super(ExceptionReason.PRODUCT_NOT_FOUND, logDetails);
  }

}
