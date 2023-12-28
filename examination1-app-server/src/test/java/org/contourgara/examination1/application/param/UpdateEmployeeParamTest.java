package org.contourgara.examination1.application.param;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class UpdateEmployeeParamTest {
  @ParameterizedTest
  @MethodSource("createData")
  void モデルに変換できる(UpdateEmployeeParam sut, Employee expected) {
    // setup
    Employee employee = new Employee(new EmployeeId("1"), "Taro", "Yamada");

    // execute
    Employee actual = sut.convertToModel(employee);

    // assert
    assertThat(actual).isEqualTo(expected);
  }

  private static Stream<Arguments> createData() {
    return Stream.of(
        Arguments.of(
            new UpdateEmployeeParam("1", null, "Yamamoto"),
            new Employee(new EmployeeId("1"), "Taro", "Yamamoto")
        ),
        Arguments.of(
            new UpdateEmployeeParam("1", "Saburo", null),
            new Employee(new EmployeeId("1"), "Saburo", "Yamada")
        )
    );
  }
}
