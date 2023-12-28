package org.contourgara.examination1.infrastructure.repository;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;
import org.contourgara.examination1.infrastructure.exception.QueryExecutionFailException;
import org.contourgara.examination1.infrastructure.mapper.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
      doReturn(1)
          .when(mapper)
          .create(new EmployeeEntity("3", "Hanako", "Shirato"));

      // execute
      Employee actual = sut.create(new Employee(new EmployeeId("3"), "Hanako", "Shirato"));

      // assert
      Employee expected = new Employee(new EmployeeId("3"), "Hanako", "Shirato");

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 新規登録に失敗した場合() {
      // setup
      doReturn(0)
          .when(mapper)
          .create(new EmployeeEntity("1", "Taro", "Yamada"));

      Employee employee = new Employee(new EmployeeId("1"), "Taro", "Yamada");

      // execute & assert
      assertThatThrownBy(() -> sut.create(employee))
          .isInstanceOf(QueryExecutionFailException.class)
          .hasMessage("クエリが正常に実行できませんでした。");
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        1
        2
        """)
    void 次のシーケンスを取得できる(Long expected) {
      // setup
      doReturn(expected)
          .when(mapper)
          .getNextSequence();

      // execute
      Long actual = sut.getNextSequence();

      // assert
      assertThat(actual).isEqualTo(expected);
    }
  }

  @Nested
  class 更新 {
    @Test
    void 更新できる() {
      // setup
      doReturn(1)
          .when(mapper)
          .update(new EmployeeEntity("1", "Taro", "Yamamoto"));

      Employee employee = new Employee(new EmployeeId("1"), "Taro", "Yamamoto");

      // execute & assert
      assertThatCode(() -> sut.update(employee))
          .doesNotThrowAnyException();
    }

    @Test
    void 更新に失敗した場合() {
      // setup
      doReturn(0)
          .when(mapper)
          .update(new EmployeeEntity("1", "Taro", "Yamamoto"));

      Employee employee = new Employee(new EmployeeId("1"), "Taro", "Yamamoto");

      // execute & assert
      assertThatThrownBy(() -> sut.update(employee))
          .isInstanceOf(QueryExecutionFailException.class)
          .hasMessage("クエリが正常に実行できませんでした。");
    }
  }

  @Nested
  class 削除 {
    @Test
    void 削除できる() {
      // setup
      doReturn(1)
          .when(mapper)
          .delete("1");

      // execute & assert
      assertThatCode(() -> sut.delete("1"))
          .doesNotThrowAnyException();
    }

    @Test
    void 削除に失敗した場合() {
      // setup
      doReturn(0)
          .when(mapper)
          .delete("1");

      // execute & assert
      assertThatThrownBy(() -> sut.delete("1"))
          .isInstanceOf(QueryExecutionFailException.class)
          .hasMessage("クエリが正常に実行できませんでした。");
    }
  }
}
