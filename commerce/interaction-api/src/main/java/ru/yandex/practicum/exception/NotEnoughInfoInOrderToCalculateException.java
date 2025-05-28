package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughInfoInOrderToCalculateException extends ApiException {

  public NotEnoughInfoInOrderToCalculateException(String logDetail) {
    super(ExceptionReason.NOT_ENOUGH_ORDER_INFO_TO_CALCULATE, logDetail);
  }
}
