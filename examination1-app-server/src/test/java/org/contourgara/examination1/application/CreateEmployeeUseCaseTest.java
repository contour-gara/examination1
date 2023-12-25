package org.contourgara.examination1.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateEmployeeUseCaseTest {
  @InjectMocks
  CreateEmployeeUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員の新規登録ができる() {
    // setup
    CreateEmployeeParam createEmployeeParam = new CreateEmployeeParam("Hanako", "Shirato");

    doReturn(3L)
        .when(repository)
        .getNextSequence();

    doReturn(new Employee(new EmployeeId("3"), "Hanako", "Shirato"))
        .when(repository)
        .create(createEmployeeParam.convertToModel(3L));

    // execute
    Employee actual = sut.execute(createEmployeeParam);

    // assert
    Employee expected = new Employee(new EmployeeId("3"), "Hanako", "Shirato");

    assertThat(actual).isEqualTo(expected);
  }
}
