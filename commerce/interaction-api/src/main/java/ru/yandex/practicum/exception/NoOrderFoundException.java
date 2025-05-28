package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoOrderFoundException extends ApiException {

  public NoOrderFoundException(final String logDetails) {
    super(ExceptionReason.NO_ORDER_FOUND, logDetails);
  }

}
