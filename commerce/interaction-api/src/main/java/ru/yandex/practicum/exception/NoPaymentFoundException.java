package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoPaymentFoundException extends ApiException {

  public NoPaymentFoundException(final String logDetails) {
    super(ExceptionReason.NO_PAYMENT_FOUND, logDetails);
  }

}
