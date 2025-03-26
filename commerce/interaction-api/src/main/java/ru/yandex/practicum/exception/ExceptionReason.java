package ru.yandex.practicum.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Defines the business exception reasons.
 */
@Getter
@AllArgsConstructor
public enum ExceptionReason {

  PRODUCT_NOT_FOUND( "No such Product in the store.", HttpStatus.NOT_FOUND);

  private final String code;
  private final String message;
  private final HttpStatus status;

  ExceptionReason(final String message, final HttpStatus status) {
    this.code = this.name();;
    this.message = message;
    this.status = status;
  }

}
