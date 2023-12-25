package org.contourgara.examination1.application.param;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;

/**
 * CreateEmployeeParam は従業員を新規登録する際のユースケースの引数として用います。
 *
 * @param firstName 新規登録する従業員の名前。
 * @param lastName 新規登録する従業員の名字。
 */
public record CreateEmployeeParam(String firstName, String lastName) {
  /**
   * 従業員モデルに変換します。
   *
   * @return Employee。従業員 ID は空文字です。
   */
  public Employee convertToModel(Long id) {
    return new Employee(EmployeeId.of(id), firstName, lastName);
  }
}
