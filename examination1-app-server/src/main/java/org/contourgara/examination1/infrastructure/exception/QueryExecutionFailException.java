package org.contourgara.examination1.infrastructure.exception;

public class QueryExecutionFailException extends RuntimeException {
  private final String id;

  public QueryExecutionFailException(String id) {
    super(String.format("id = %s", id));
    this.id = id;
  }
}
