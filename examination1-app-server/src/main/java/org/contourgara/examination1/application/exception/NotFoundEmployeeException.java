package org.contourgara.examination1.application.exception;

import lombok.Getter;

@Getter
public class NotFoundEmployeeException extends RuntimeException {
  private final String id;

  public NotFoundEmployeeException(String id) {
    this.id = id;
  }
}
