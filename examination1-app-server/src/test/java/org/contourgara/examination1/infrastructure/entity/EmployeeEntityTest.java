package org.contourgara.examination1.infrastructure.entity;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmployeeEntityTest {
  @Test
  void ドメインモデルに変換できる() {
    // setup
    EmployeeEntity sut = new EmployeeEntity("1", "Taro", "Yamada");

    // execute
    Employee actual = sut.convertToModel();

    // assert
    Employee expected = new Employee(new EmployeeId("1"), "Taro", "Yamada");

    assertThat(actual).isEqualTo(expected);
  }
}
