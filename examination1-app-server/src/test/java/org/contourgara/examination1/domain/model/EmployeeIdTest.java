package org.contourgara.examination1.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.contourgara.examination1.domain.exception.DomainValidateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EmployeeIdTest {
  @ParameterizedTest
  @CsvSource(textBlock = """
    0
    1000000000
    """)
  void idが無効な数字の場合例外が飛ぶ(String value) {
    // execute & assert
    assertThatCode(() -> new EmployeeId(value))
      .isInstanceOf(DomainValidateException.class)
      .hasMessage(String.format("従業員 ID は 1 以上 999999999 以下です。[id = %s]", value));
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
      .isInstanceOf(DomainValidateException.class)
      .hasMessage(String.format("従業員 ID は数字です。[id = %s]", value));
  }

  @Test
  void Long型からオブジェクトを生成できる() {
    // setup
    EmployeeId sut = EmployeeId.of(3L);

    // execute
    String actual = sut.value();

    // expected
    String expected = "3";

    assertThat(actual).isEqualTo(expected);
  }
}
