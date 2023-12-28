package org.contourgara.examination1.infrastructure.exception;

public class QueryExecutionFailException extends RuntimeException {
  public QueryExecutionFailException(String message) {
    super(message);
  }
}
