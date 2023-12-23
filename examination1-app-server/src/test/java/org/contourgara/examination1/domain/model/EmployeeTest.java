package org.contourgara.examination1.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {
  private static final EmployeeId EMPLOYEE_ID = new EmployeeId("1");
  private static final String FIRST_NAME = "Taro";
  private static final String LAST_NAME = "Yamada";

  @Test
  void 従業員IDがnullの場合例外が飛ぶ() {
    // execute & assert
    assertThatCode(() -> new Employee(null, FIRST_NAME, LAST_NAME))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("従業員 ID は null であってはなりません。");
  }

  @Test
  void 名前がnullの場合例外が飛ぶ() {
    // execute & assert
    assertThatCode(() -> new Employee(EMPLOYEE_ID, null, LAST_NAME))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("名前は null であってはなりません。");
  }

  @Test
  void 名字がnullの場合例外が飛ぶ() {
    // execute & assert
    assertThatCode(() -> new Employee(EMPLOYEE_ID, FIRST_NAME, null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("名字は null であってはなりません。");
  }

  @Test
  void 名前が100文字以上の場合例外が飛ぶ() {
    // execute & assert
    assertThatCode(() -> new Employee(EMPLOYEE_ID, "a".repeat(101), LAST_NAME))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("名前は 100 文字以内です。");
  }

  @Test
  void 名字が100文字以上の場合例外が飛ぶ() {
    // execute & assert
    assertThatCode(() -> new Employee(EMPLOYEE_ID, FIRST_NAME, "a".repeat(101)))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("名字は 100 文字以内です。");
  }
}
