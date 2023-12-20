package org.contourgara.examination1.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class EmployeeIdTest {
  @ParameterizedTest
  @CsvSource(textBlock = """
    0
    1000000000
    """)
  void idが無効な数字の場合例外が飛ぶ(String value) {
    assertThatCode(() -> new EmployeeId(value))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("従業員 ID は 1 以上 999999999 以下です。");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
    1
    999999999
    """)
  void idが有効な数字の場合例外が飛ばない(String value) {
    // execute & assert
    assertThatCode(() -> new EmployeeId(value))
      .doesNotThrowAnyException();
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
    assertThatCode(() -> new EmployeeId(value))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("従業員 ID は数字です。");
  }
}
