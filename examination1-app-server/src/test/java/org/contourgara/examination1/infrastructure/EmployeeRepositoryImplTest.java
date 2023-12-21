package org.contourgara.examination1.infrastructure;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryImplTest {
  @Autowired
  EmployeeRepository sut;

  @Test
  void 全件取得する場合() {
    // execute
    List<Employee> actual = sut.findAll();

    // assert
    List<Employee> expected = List.of(
        new Employee(new EmployeeId("1"), "Taro", "Yamada"),
        new Employee(new EmployeeId("2"), "Jiro", "Yamada")
    );

    assertThat(actual).isEqualTo(expected);
  }
}
