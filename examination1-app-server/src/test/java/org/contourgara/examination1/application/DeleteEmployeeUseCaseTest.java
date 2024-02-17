package org.contourgara.examination1.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteEmployeeUseCaseTest {
  @InjectMocks
  DeleteEmployeeUseCase sut;

  @Mock
  EmployeeRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員を削除できる() {
    // setup
    doReturn(Optional.of(new Employee(new EmployeeId("1"), "Taro", "Yamada")))
        .when(repository)
        .findById("1");

    // execute & assert
    assertThatCode(() -> sut.execute("1"))
        .doesNotThrowAnyException();
  }

  @Test
  void 削除したい従業員が存在しない場合() {
    // setup
    doReturn(Optional.empty())
        .when(repository)
        .findById("0");

    // execute & assert
    assertThatThrownBy(() -> sut.execute("0"))
        .isInstanceOf(NotFoundEmployeeException.class);
  }
}
