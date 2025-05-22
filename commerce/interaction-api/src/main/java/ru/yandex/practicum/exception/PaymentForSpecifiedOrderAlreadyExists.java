package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PaymentForSpecifiedOrderAlreadyExists extends ApiException {

  public PaymentForSpecifiedOrderAlreadyExists(String logDetails) {
    super(ExceptionReason.PAYMENT_FOR_SPECIFIED_ORDER_ALREADY_EXIST, logDetails);
  }
}
