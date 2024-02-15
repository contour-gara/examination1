package org.contourgara.examination1.domain.model;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;
import org.contourgara.examination1.domain.exception.DomainValidateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class EmployeeTest {
  @Test
  void 従業員IDが不適切な場合例外が飛ぶ() {
    // execute & assert
    assertThatCode(() -> new Employee(null, "Taro", "Yamada"))
        .isInstanceOf(DomainValidateException.class)
        .hasMessage("従業員 ID は null であってはなりません。");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
          , Yamada, firstName,
        '', Yamada, firstName,  ''
       ' ', Yamada, firstName, ' '
       a1-, Yamada, firstName, a1-
      Taro,       ,  lastName,
      Taro,     '',  lastName,  ''
      Taro,    ' ',  lastName, ' '
      Taro,    a1-,  lastName, a1-
      """)
  void 名前や名字が不適切な場合例外が飛ぶ(
      String firstName, String lastName, String validateFailedName, String validateFailedValue
  ) {
    // setup
    EmployeeId employeeId = new EmployeeId("1");

    // execute & assert
    assertThatThrownBy(() -> new Employee(employeeId, firstName, lastName))
        .isInstanceOf(DomainValidateException.class)
        .hasMessage(
            String.format(
                "%s が不適切です。[%s = %s]", validateFailedName, validateFailedName, validateFailedValue
            )
        );
  }

  @ParameterizedTest
  @MethodSource("createData")
  void 名前が100文字以上の場合例外が飛ぶ(
      String firstName, String lastName, String validateFailedName, String validateFailedValue
  ) {
    // setup
    EmployeeId employeeId = new EmployeeId("1");

    // execute & assert
    assertThatThrownBy(() -> new Employee(employeeId, firstName, lastName))
        .isInstanceOf(DomainValidateException.class)
        .hasMessage(
            String.format(
                "%s が 100 文字を超えています。[%s = %s]", validateFailedName, validateFailedName, validateFailedValue
            )
        );
  }

  private static Stream<Arguments> createData() {
    return Stream.of(
        Arguments.of("a".repeat(101), "Yamada", "firstName", "a".repeat(101)),
        Arguments.of("Taro", "a".repeat(101), "lastName", "a".repeat(101))
    );
  }
}
