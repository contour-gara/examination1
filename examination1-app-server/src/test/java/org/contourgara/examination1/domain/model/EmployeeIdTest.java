package org.contourgara.examination1.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmployeeIdTest {
  @Test
  void idが0の場合例外が飛ぶ() {
    // execute & assert
    assertThatThrownBy(() -> new EmployeeId("0"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("従業員 ID は 1 以上 999999999 以下です。");
  }

  @Test
  void idが1000000000の場合例外が飛ぶ() {
    // execute & assert
    assertThatThrownBy(() -> new EmployeeId("1000000000"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("従業員 ID は 1 以上 999999999 以下です。");
  }
}
