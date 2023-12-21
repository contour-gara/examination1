package org.contourgara.examination1.infrastructure.repository;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;
import org.contourgara.examination1.infrastructure.mapper.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EmployeeRepositoryImplTest {
  @InjectMocks
  EmployeeRepositoryImpl sut;

  @Mock
  EmployeeMapper mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Nested
  class 全件取得 {
    @Test
    void 全件取得できる() {
      // setup
      doReturn(
          List.of(
              new EmployeeEntity("1", "Taro", "Yamada"),
              new EmployeeEntity("2", "Jiro", "Yamada")
          )
      ).when(mapper).findAll();

      // execute
      List<Employee> actual = sut.findAll();

      // assert
      List<Employee> expected = List.of(
          new Employee(new EmployeeId("1"), "Taro", "Yamada"),
          new Employee(new EmployeeId("2"), "Jiro", "Yamada")
      );

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void データがない場合() {
      // setup
      doReturn(emptyList()).when(mapper).findAll();

      // execute
      List<Employee> actual = sut.findAll();

      // assert
      List<Employee> expected = emptyList();

      assertThat(actual).isEqualTo(expected);
    }
  }
}
