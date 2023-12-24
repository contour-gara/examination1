package org.contourgara.examination1.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class CreateEmployeeUseCaseTest {
  @InjectMocks
  CreateEmployeeUseCase sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員の新規登録ができる() {
    // setup
    CreateEmployeeParam createEmployeeParam = new CreateEmployeeParam("Hanako", "Shirato");

    // execute
    Employee actual = sut.execute(createEmployeeParam);

    // assert
    Employee expected = new Employee(new EmployeeId("3"), "Hanako", "Shirato");

    assertThat(actual).isEqualTo(expected);
  }
}
