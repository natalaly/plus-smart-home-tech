package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDeliveryFoundException extends ApiException {

  public NoDeliveryFoundException(final String logDetails) {
    super(ExceptionReason.NO_DELIVERY_FOUND, logDetails);
  }

}
