package org.contourgara.examination1.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.contourgara.examination1.domain.model.Employee;

/**
 * AllEmployeesResponse は従業員のレスポンス用オブジェクトを複数格納できるオブジェクトです。
 *
 * @param employeeResponses 従業員のレスポンス用オブジェクトの List。
 */
public record AllEmployeesResponse(
    @JsonProperty("employees") List<EmployeeResponse> employeeResponses
) {
  /**
   * AllEmployeeResponse を初期化します。
   *
   * @param employees 従業員オブジェクトの List
   * @return AllEmployeesResponse。
   */
  public static AllEmployeesResponse of(List<Employee> employees) {
    return new AllEmployeesResponse(employees.stream().map(EmployeeResponse::of).toList());
  }
}
