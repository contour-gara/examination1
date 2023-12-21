package org.contourgara.examination1.infrastructure.mapper;

import static org.assertj.core.api.Assertions.*;

import java.sql.DriverManager;
import java.util.List;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;
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

  @Test
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
  @ExpectedDataSet(value = "datasets/expected/migration-check.yml")
  void 空のテーブルが作成される() {
    // 何もしません
  }
}
