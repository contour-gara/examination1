package org.contourgara.examination1.application;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;

class FindEmployeeByIdUseCaseTest {
  @InjectMocks
  FindEmployeeByIdUseCase sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員が取得できる() {
    // setup
    // execute
    Employee actual = sut.execute("1");

    // assert
    Employee expected = new Employee(new EmployeeId("1"), "Taro", "Yamada");

    assertThat(actual).isEqualTo(expected);
  }
}
