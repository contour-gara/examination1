package org.contourgara.examination1.application.param;

import static java.util.Objects.nonNull;

import org.contourgara.examination1.domain.model.Employee;

public record UpdateEmployeeParam(String id, String firstName, String lastName) {
  public Employee convertToModel(Employee employee) {
    return new Employee(
        employee.employeeId(),
        nonNull(firstName) ? firstName : employee.firstName(),
        nonNull(lastName) ? lastName : employee.lastName()
    );
  }
}
