package org.contourgara.examination1.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AllEmployeesResponse(
  @JsonProperty("employees") List<EmployeeResponse> employeeResponses
  ) {
}
