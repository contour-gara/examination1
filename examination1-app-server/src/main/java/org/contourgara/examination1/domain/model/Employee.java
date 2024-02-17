package org.contourgara.examination1.domain.model;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isAlpha;

import org.contourgara.examination1.common.DomainErrorMessage;
import org.contourgara.examination1.domain.exception.DomainValidateException;

/**
 * Employee クラスは従業員を表すモデルです。
 *
 * @param employeeId {@link EmployeeId}。null であってはなりません。
 * @param firstName 名前。アルファベットのみで空白や null であってはなりません。
 * @param lastName 名字。アルファベットのみで空白や null であってはなりません。
 */
public record Employee(
    EmployeeId employeeId,
    String firstName,
    String lastName
) {
  /**
   * Employee を初期化します。
   *
   * @param employeeId {@link EmployeeId}。null であってはなりません。
   * @param firstName 名前。アルファベットのみで空白や null であってはなりません。
   * @param lastName 名字。アルファベットのみで空白や null であってはなりません。
   */
  public Employee {
    if (isNull(employeeId)) {
      throw new DomainValidateException(DomainErrorMessage.EMPLOYEE_ID_NULL.getMessage());
    }

    if (!isAlpha(firstName)) {
      throw new DomainValidateException(
          String.format(DomainErrorMessage.EMPLOYEE_FIRST_NAME_ALPHA.getMessage(), firstName)
      );
    }

    if (!isAlpha(lastName)) {
      throw new DomainValidateException(
          String.format(DomainErrorMessage.EMPLOYEE_LAST_NAME_ALPHA.getMessage(), lastName)
      );
    }

    if (firstName.length() > 100) {
      throw new DomainValidateException(
          String.format(DomainErrorMessage.EMPLOYEE_FIRST_NAME_RANGE.getMessage(), firstName)
      );
    }

    if (lastName.length() > 100) {
      throw new DomainValidateException(
          String.format(DomainErrorMessage.EMPLOYEE_LAST_NAME_RANGE.getMessage(), lastName)
      );
    }
  }
}
