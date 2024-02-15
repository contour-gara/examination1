package org.contourgara.examination1.domain.model;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isAlpha;

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
    if (isNull(employeeId)) throw new DomainValidateException("従業員 ID は null であってはなりません。");

    if (!isAlpha(firstName)) {
      throw new DomainValidateException(
          String.format("firstName が不適切です。[firstName = %s]", firstName)
      );
    }

    if (!isAlpha(lastName)) {
      throw new DomainValidateException(
          String.format("lastName が不適切です。[lastName = %s]", lastName)
      );
    }

    if (firstName.length() > 100) {
      throw new DomainValidateException(
          String.format("firstName が 100 文字を超えています。[firstName = %s]", firstName)
      );
    }

    if (lastName.length() > 100) {
      throw new DomainValidateException(
          String.format("lastName が 100 文字を超えています。[lastName = %s]", lastName)
      );
    }
  }
}
