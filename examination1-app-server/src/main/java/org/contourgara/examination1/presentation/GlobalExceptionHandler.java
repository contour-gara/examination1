package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundEmployeeException.class)
  @ResponseStatus(BAD_REQUEST)
  public void handleNotFoundEmployeeException(NotFoundEmployeeException e) {
  }
}
