package org.contourgara.examination1.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DomainErrorMessage は、ドメインで投げられる例外のメッセージを管理します。
 */
@Getter
@RequiredArgsConstructor
public enum DomainErrorMessage {
  EMPLOYEE_ID_VALUE_NUMERIC("value must be numeric. [value = %s]"),
  EMPLOYEE_ID_VALUE_RANGE("value length must be between 1 and 999999999. [value = %s]"),
  EMPLOYEE_ID_NULL("employeeId must not be null. [employeeId = null]"),
  EMPLOYEE_FIRST_NAME_ALPHA("firstName must be alphabet. [firstName = %s]"),
  EMPLOYEE_FIRST_NAME_RANGE("firstName length must be between 1 and 100. [firstName = %s]"),
  EMPLOYEE_LAST_NAME_ALPHA("lastName must be alphabet. [lastName = %s]"),
  EMPLOYEE_LAST_NAME_RANGE("lastName length must be between 1 and 100. [lastName = %s]");

  private final String message;
}
