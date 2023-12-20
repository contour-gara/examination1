package org.contourgara.examination1.presentation.response;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmployeeResponseTest {
  @Test
  void EmployeeからEmployeeResponseが作成できる() {
    // setup
    Employee employee = new Employee(new EmployeeId("1"), "Taro", "Yamada");

    // execute
    EmployeeResponse actual = EmployeeResponse.of(employee);

    // assert
    EmployeeResponse expected = new EmployeeResponse("1", "Taro", "Yamada");

    assertThat(actual).isEqualTo(expected);
  }
}
