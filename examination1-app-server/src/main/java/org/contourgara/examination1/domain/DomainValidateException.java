package org.contourgara.examination1.domain;

public class DomainValidateException extends IllegalArgumentException {
  public DomainValidateException(String message) {
    super(message);
  }
}
