package org.contourgara.examination1.application.param;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;

public record CreateEmployeeParam(String firstName, String lastName) {
  public Employee convertToModel(String id) {
    return new Employee(new EmployeeId(id), firstName, lastName);
  }
}
