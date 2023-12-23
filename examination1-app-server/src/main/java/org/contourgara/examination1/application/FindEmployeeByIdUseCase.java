
package org.contourgara.examination1.application;

import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindEmployeeByIdUseCase {
  public Employee execute(String id) {
    Optional<Employee> tmp;

    if (id.equals("1")) {
      tmp = Optional.of(new Employee(new EmployeeId("1"), "Taro", "Yamada"));
    } else if (id.equals("2")) {
      tmp = Optional.of(new Employee(new EmployeeId("2"), "Jiro", "Yamada"));
    } else {
      tmp = Optional.empty();
    }

    return tmp.orElseThrow(() -> new NotFoundEmployeeException(id));
  }
}
