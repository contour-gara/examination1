package org.contourgara.examination1.application.param;

import static java.util.Objects.nonNull;

import org.contourgara.examination1.domain.model.Employee;

/**
 * UpdateEmployeeParam は従業員を更新する際のユースケースの引数として用います。
 *
 * @param id 更新する従業員 ID。
 * @param firstName 更新する従業員の名前。
 * @param lastName 更新する従業員の名字。
 */
public record UpdateEmployeeParam(String id, String firstName, String lastName) {
  /**
   * データベースに登録されていた従業員モデルから、変更箇所のみ変更して返します。
   *
   * @param employee 従業員情報。
   * @return {@link Employee}。更新済みの従業員モデルです。
   */
  public Employee convertToModel(Employee employee) {
    return new Employee(
        employee.employeeId(),
        nonNull(firstName) ? firstName : employee.firstName(),
        nonNull(lastName) ? lastName : employee.lastName()
    );
  }
}
