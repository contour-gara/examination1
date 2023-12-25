package org.contourgara.examination1.domain.model;

import static java.util.Objects.isNull;

// TODO: ガード条件見直し

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
    if (isNull(firstName)) throw new IllegalArgumentException("名前は null であってはなりません。");
    if (isNull(lastName)) throw new IllegalArgumentException("名字は null であってはなりません。");
    if (firstName.length() > 100) throw new IllegalArgumentException("名前は 100 文字以内です。");
    if (lastName.length() > 100) throw new IllegalArgumentException("名字は 100 文字以内です。");
  }
}
