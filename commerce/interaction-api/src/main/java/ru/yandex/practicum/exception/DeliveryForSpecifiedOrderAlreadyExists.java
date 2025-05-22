package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DeliveryForSpecifiedOrderAlreadyExists extends ApiException {

  public DeliveryForSpecifiedOrderAlreadyExists(final String logDetails) {
    super(ExceptionReason.DELIVERY_FOR_SPECIFIED_ORDER_ALREADY_EXIST, logDetails);
  }
}
