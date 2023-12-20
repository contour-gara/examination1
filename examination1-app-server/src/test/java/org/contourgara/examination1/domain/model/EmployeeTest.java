package org.contourgara.examination1.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmployeeTest {
  private static final EmployeeId EMPLOYEE_ID = new EmployeeId("1");
  private static final String FIRST_NAME = "Taro";
  private static final String LAST_NAME = "Yamada";

  @Test
  void 従業員IDがnullの場合例外が飛ぶ() {
    // setup
    assertThatCode(() -> new Employee(null, FIRST_NAME, LAST_NAME))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("従業員 ID は null であってはなりません。");
  }

  @Test
  void 名前がnullの場合例外が飛ぶ() {
    // setup
    assertThatCode(() -> new Employee(EMPLOYEE_ID, null, LAST_NAME))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("名前は null であってはなりません。");
  }
}
