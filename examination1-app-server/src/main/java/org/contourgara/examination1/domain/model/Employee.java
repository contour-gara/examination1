package org.contourgara.examination1.domain.model;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isAlpha;

/**
 * Employee クラスは従業員を表すモデルです。
 *
 * @param employeeId 従業員 ID モデル。null であってはなりません。
 * @param firstName 名前。null であってはなりません。
 * @param lastName 名字。null であってはなりません。
 */
public record Employee(
    EmployeeId employeeId,
    String firstName,
    String lastName
) {
  /**
   * Employee を初期化します。
   *
   * @param employeeId 従業員 ID モデル。null であってはなりません。
   * @param firstName 名前。null であってはなりません。
   * @param lastName 名字。null であってはなりません。
   */
  public Employee {
    if (isNull(employeeId)) throw new IllegalArgumentException("従業員 ID は null であってはなりません。");
    if (!isAlpha(firstName)) throw new IllegalArgumentException(String.format("firstName が不適切です。[firstName = %s]", firstName));
    if (!isAlpha(lastName)) throw new IllegalArgumentException(String.format("lastName が不適切です。[lastName = %s]", lastName));
    if (firstName.length() > 100) throw new IllegalArgumentException(String.format("firstName が 100 文字を超えています。[firstName = %s]", firstName));
    if (lastName.length() > 100) throw new IllegalArgumentException(String.format("lastName が 100 文字を超えています。[lastName = %s]", lastName));
  }
}
