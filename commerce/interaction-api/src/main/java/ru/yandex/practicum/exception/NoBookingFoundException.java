package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoBookingFoundException extends ApiException {

  public NoBookingFoundException(String logDetails) {
    super(ExceptionReason.NO_BOOKING_FOUND, logDetails);

  }
}
