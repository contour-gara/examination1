package org.contourgara.examination1.infrastructure.mapper;

import static org.assertj.core.api.Assertions.*;
import static java.util.Collections.emptyList;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import java.sql.DriverManager;
import java.util.List;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DBRider
@DBUnit
@SpringBootTest
class EmployeeMapperTest {
  private static final String DB_URL = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false";
  private static final String DB_USER = "sa";
  private static final String DB_PASSWORD = "sa";

  private static final ConnectionHolder connectionHolder =
      () -> DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

  @Autowired
  EmployeeMapper sut;

  @Nested
  class 全件取得 {
    @Test
    @DataSet(value = "datasets/setup/2-employee.yml")
    @ExpectedDataSet(value = "datasets/expected/2-employee.yml")
    void 全件取得できる() {
      // execute
      List<EmployeeEntity> actual = sut.findAll();

      // assert
      List<EmployeeEntity> expected = List.of(
          new EmployeeEntity("1", "Taro", "Yamada"),
          new EmployeeEntity("2", "Jiro", "Yamada")
      );

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(value = "datasets/setup/empty-table.yml")
    @ExpectedDataSet(value = "datasets/expected/empty-table.yml")
    void データがない場合() {
      // execute
      List<EmployeeEntity> actual = sut.findAll();

      // assert
      List<EmployeeEntity> expected = emptyList();

      assertThat(actual).isEqualTo(expected);
    }
  }

  @Nested
  class ID検索 {
    @Test
    @DataSet(value = "datasets/setup/1-employee.yml")
    @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
    void ID検索できる() {
      // execute
      EmployeeEntity actual = sut.findById("1");

      // assert
      EmployeeEntity expected = new EmployeeEntity("1", "Taro", "Yamada");

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(value = "datasets/setup/2-employee.yml")
    @ExpectedDataSet(value = "datasets/expected/2-employee.yml")
    void テーブルにデータが複数あってもID検索できる() {
      // execute
      EmployeeEntity actual = sut.findById("1");

      // assert
      EmployeeEntity expected = new EmployeeEntity("1", "Taro", "Yamada");

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(value = "datasets/setup/1-employee.yml")
    @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
    void ID検索で見つからない場合() {
      // execute
      EmployeeEntity actual = sut.findById("0");

      // assert
      assertThat(actual).isNull();
    }
  }

  @Nested
  class シーケンス取得 {
    @Test
    @DataSet(
        value = "datasets/setup/empty-table.yml",
        executeScriptsBefore = "datasets/setup/set-sequence-from-1.sql"
    )
    @ExpectedDataSet(value = "datasets/expected/empty-table.yml")
    void テーブルが空の場合() {
      // execute
      Long actual = sut.getNextSequence();

      // assert
      Long expected = 1L;

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(
        value = "datasets/setup/1-employee.yml",
        executeScriptsBefore = "datasets/setup/set-sequence-from-2.sql"
    )
    @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
    void テーブルに1件登録されている場合() {
      // execute
      Long actual = sut.getNextSequence();

      // assert
      Long expected = 2L;

      assertThat(actual).isEqualTo(expected);
    }
  }

  @Test
  @DataSet(value = "datasets/setup/empty-table.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
  void 新規登録できる() {
    // execute
    sut.create(new EmployeeEntity("1", "Taro", "Yamada"));

    // assert
    assertThat(sut).isNotNull();
  }

  @Test
  @DataSet(value = "datasets/setup/1-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee-updated.yml")
  void 更新できる() {
    // setup
    EmployeeEntity entity = new EmployeeEntity("1", "Taro", "Yamamoto");

    // execute
    Integer actual = sut.update(entity);

    // assert
    Integer expected = 1;

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DataSet(value = "datasets/setup/1-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/empty-table.yml")
  void 削除できる() {
    // execute
    Integer actual = sut.delete("1");

    // assert
    Integer expected = 1;

    assertThat(actual).isEqualTo(expected);
  }
}
