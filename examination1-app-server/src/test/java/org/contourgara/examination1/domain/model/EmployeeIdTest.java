package org.contourgara.examination1.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

  @ParameterizedTest
  @CsvSource(textBlock = """
    null
    数字
    abc
    ''
    ' '
    """)
  void idが数字でない場合例外が飛ぶ(String value) {
    // execute & assert
    assertThatThrownBy(() -> new EmployeeId(value))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("従業員 ID は数字です。");
  }
}
