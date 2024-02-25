package org.contourgara.examination1.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * MessageCode は、ログや例外のメッセージを管理します。
 */
@Getter
@RequiredArgsConstructor
public enum ErrorMessageCode {
  UNEXPECTED_ERROR("0000", "unexpected exception has occurred. [%s]"),
  VALIDATE_ERROR_IN_PRESENTATION("0002", "request validation error is occurred."),
  NOT_FOUND_EMPLOYEE("0003", "specified employee [id = %s] is not found."),
  QUERY_EXECUTION_ERROR("0004", "query execution failed. [id = %s]"),
  DATA_ACCESS_ERROR("0005", "unexpected exception has occurred on Database connection.");

  private final String code;
  private final String message;
}
