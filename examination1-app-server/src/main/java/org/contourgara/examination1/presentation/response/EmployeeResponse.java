package org.contourgara.examination1.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.contourgara.examination1.domain.model.Employee;

/**
 * EmployeeResponse は従業員を表すレスポンス用オブジェクトです。
 *
 * @param id 従業員 ID。
 * @param firstName 名前。
 * @param lastName 名字。
 */
public record EmployeeResponse(
    @JsonProperty("id") String id,
    @JsonProperty("firstName") String firstName,
    @JsonProperty("lastName") String lastName
) {
  /**
   * EmployeeResponse を初期化します。
   *
   * @param employee 従業員オブジェクト。
   * @return 従業員レスポンス用オブジェクト。
   */
  public static EmployeeResponse of(Employee employee) {
    return new EmployeeResponse(
        employee.employeeId().getValue(),
        employee.firstName(),
        employee.lastName()
    );
  }
}
