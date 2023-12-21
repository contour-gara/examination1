package org.contourgara.examination1.infrastructure;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class EmployeeRepositoryImplTest {
  @InjectMocks
  EmployeeRepositoryImpl sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

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
