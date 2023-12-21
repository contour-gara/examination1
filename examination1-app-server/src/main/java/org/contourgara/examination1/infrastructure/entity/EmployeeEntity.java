package org.contourgara.examination1.infrastructure.entity;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;

/**
 * EmployeeEntity は従業員を表すエンティティです。
 * データベースでの入出力に使用します。
 *
 * @param id 従業員 ID。
 * @param firstName 名前。
 * @param lastName 名字。
 */
public record EmployeeEntity(String id, String firstName, String lastName) {
  /**
   * 従業員エンティティを従業員モデルに変換します。
   *
   * @return 従業員モデル。
   */
  public Employee convertToModel() {
    return new Employee(new EmployeeId(id), firstName, lastName);
  }
}
