package org.contourgara.examination1.application;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.springframework.stereotype.Service;

@Service
public class FindAllEmployeesUseCase {
  public List<Employee> execute() {
    return List.of(
        new Employee(new EmployeeId("1"), "Taro", "Yamada"),
        new Employee(new EmployeeId("2"), "Jiro", "Yamada")
    );
  }
}
