package org.contourgara.examination1.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.contourgara.examination1.domain.model.Employee;

import java.util.List;

public record AllEmployeesResponse(
  @JsonProperty("employees") List<EmployeeResponse> employeeResponses
  ) {
  public static AllEmployeesResponse of(List<Employee> employees) {
    return new AllEmployeesResponse(List.of());
  }
}
