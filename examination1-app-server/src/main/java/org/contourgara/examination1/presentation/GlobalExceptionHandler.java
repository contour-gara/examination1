package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.presentation.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundEmployeeException.class)
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleNotFoundEmployeeException(NotFoundEmployeeException e) {
    return new ErrorResponse(
        "0003",
        String.format("specified employee [id = %s] is not found.", e.getId()),
        new ArrayList<>()
    );
  }
}
