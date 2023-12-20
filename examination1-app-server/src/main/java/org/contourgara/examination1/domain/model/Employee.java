package org.contourgara.examination1.domain.model;

import static java.util.Objects.isNull;

/**
 * Employee クラスは従業員を表すクラスです。
 *
 * @param employeeId 従業員 ID です。null であってはなりません。
 * @param firstName 名前です。null であってはなりません。
 * @param lastName 名字です。null であってはなりません。
 */
public record Employee(
    EmployeeId employeeId,
    String firstName,
    String lastName
) {
  public Employee {
    if (isNull(employeeId)) throw new IllegalArgumentException("従業員 ID は null であってはなりません。");
    if (isNull(firstName)) throw new IllegalArgumentException("名前は null であってはなりません。");
  }
}
