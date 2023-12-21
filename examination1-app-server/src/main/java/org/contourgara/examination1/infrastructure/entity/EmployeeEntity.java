package org.contourgara.examination1.infrastructure.entity;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;

public record EmployeeEntity(String id, String firstName, String lastName) {
  public Employee convertToModel() {
    return new Employee(new EmployeeId("1"), "Taro", "Yamada");
  }
}
