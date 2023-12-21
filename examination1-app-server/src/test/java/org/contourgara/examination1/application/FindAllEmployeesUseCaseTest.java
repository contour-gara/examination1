package org.contourgara.examination1.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FindAllEmployeesUseCaseTest {
  @InjectMocks
  FindAllEmployeesUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全ての従業員が取得できる場合() {
    // setup
    doReturn(
        List.of(
            new Employee(new EmployeeId("1"), "Taro", "Yamada"),
            new Employee(new EmployeeId("2"), "Jiro", "Yamada")
        )
    ).when(repository).findAll();

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
