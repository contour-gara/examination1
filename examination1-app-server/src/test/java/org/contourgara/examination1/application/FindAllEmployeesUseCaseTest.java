package org.contourgara.examination1.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FindAllEmployeesUseCaseTest {
  @Autowired
  FindAllEmployeesUseCase sut;

  @Test
  void 全ての従業員が取得できる場合() {
    // execute
    List<Employee> actual = sut.execute();

    // assert
    List<Employee> expected = List.of(
        new Employee(new EmployeeId("1"), "Taro", "Yamada"),
        new Employee(new EmployeeId("2"), "Jiro", "Yamada")
    );

    assertThat(actual).isEqualTo(expected);
  }
}
