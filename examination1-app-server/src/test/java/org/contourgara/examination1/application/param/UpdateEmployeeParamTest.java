package org.contourgara.examination1.application.param;

import static org.assertj.core.api.Assertions.*;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.Test;

class UpdateEmployeeParamTest {
  @Test
  void モデルに変換できる() {
    // setup
    UpdateEmployeeParam sut = new UpdateEmployeeParam("1", null, "Yamamoto");
    Employee employee = new Employee(new EmployeeId("1"), "Taro", "Yamada");

    // execute
    Employee actual = sut.convertToModel(employee);

    // assert
    Employee expected = new Employee(new EmployeeId("1"), "Taro", "Yamamoto");

    assertThat(actual).isEqualTo(expected);
  }
}
