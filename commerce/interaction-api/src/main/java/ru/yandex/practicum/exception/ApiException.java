package ru.yandex.practicum.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import ru.yandex.practicum.exception.policy.ExceptionPolicy;

@Getter
@Setter
public class ApiException extends RuntimeException implements ExceptionPolicy {

  protected final String code;
  protected final String message;
  protected final HttpStatus httpStatus;
  protected final String logDetails;

  public ApiException(final ExceptionReason reason, final String logDetails) {
    super(reason.getMessage());
    this.code = reason.getCode();
    this.message = reason.getMessage();
    this.httpStatus = reason.getStatus();
    this.logDetails = logDetails;
  }

  public ApiException(final ExceptionReason reason, final HttpStatus overridingStatus, final String logDetails) {
    super(reason.getMessage());
    this.code = reason.getCode();
    this.message = reason.getMessage();
    this.httpStatus = overridingStatus;
    this.logDetails = logDetails;
  }

}
