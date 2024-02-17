package org.contourgara.examination1.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * NotFoundEmployeeException は従業員が見つからなかった場合に投げます。
 */
@Getter
@RequiredArgsConstructor
public class NotFoundEmployeeException extends RuntimeException {
  private final String id;
}
