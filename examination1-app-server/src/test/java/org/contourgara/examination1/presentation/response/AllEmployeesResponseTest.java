package org.contourgara.examination1.presentation.response;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.Test;

class AllEmployeesResponseTest {
  @Test
  void EmployeeのListからAllEmployeeResponseが作成できる() {
    // setup
    List<Employee> employees = List.of(
        new Employee(new EmployeeId("1"), "Taro", "Yamada"),
        new Employee(new EmployeeId("2"), "Jiro", "Yamada")
    );

    // execute
    AllEmployeesResponse actual = AllEmployeesResponse.of(employees);

    // assert
    AllEmployeesResponse expected = new AllEmployeesResponse(
        List.of(
            new EmployeeResponse("1", "Taro", "Yamada"),
            new EmployeeResponse("2", "Jiro", "Yamada")
        )
    );

    assertThat(actual).isEqualTo(expected);
  }
}
