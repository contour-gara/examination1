package org.contourgara.examination1.infrastructure.repository;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

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

  @Nested
  class ID検索 {
    @Test
    void ID検索できる() {
      // setup
      doReturn(new EmployeeEntity("1", "Taro", "Yamada"))
          .when(mapper)
          .findById("1");

      // execute
      Optional<Employee> actual = sut.findById("1");

      // assert
      Optional<Employee> expected
          = Optional.of(new Employee(new EmployeeId("1"), "Taro", "Yamada"));

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void ID検索で見つからない場合() {
      // setup
      doReturn(null)
          .when(mapper)
          .findById("0");

      // execute
      Optional<Employee> actual = sut.findById("0");

      // assert
      Optional<Employee> expected = Optional.empty();

      assertThat(actual).isEqualTo(expected);
    }
  }

  @Nested
  class 新規登録 {
    @Test
    void 新規登録できる() {
      // setup
      doReturn(3L)
          .when(mapper)
          .getNextSequence();

      // execute
      Employee actual = sut.create(new Employee(EmployeeId.createEmptyId(), "Hanako", "Shirato"));

      // assert
      Employee expected = new Employee(new EmployeeId("3"), "Hanako", "Shirato");

      assertThat(actual).isEqualTo(expected);
    }
  }
}
