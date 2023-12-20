package org.contourgara.examination1.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmployeeTest {
  private static final EmployeeId EMPLOYEE_ID = new EmployeeId("1");

  @Test
  void 名前がnullの場合例外が飛ぶ() {
    // setup
    assertThatCode(() -> new Employee(EMPLOYEE_ID, null, "Yamada"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("名前は null であってはなりません。");
  }
}
