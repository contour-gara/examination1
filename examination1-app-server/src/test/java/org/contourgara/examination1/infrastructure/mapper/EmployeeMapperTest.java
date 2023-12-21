package org.contourgara.examination1.infrastructure.mapper;

import static org.assertj.core.api.Assertions.*;

import java.sql.DriverManager;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
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

  @BeforeAll
  static void setUpAll() {
    Flyway.configure().dataSource(DB_URL, DB_USER, DB_PASSWORD).load().migrate();
  }

  @Test
  @ExpectedDataSet(value = "datasets/expected/migration-check.yml")
  void マイグレーションの挙動確認() {
    // 何もしません
  }
}
