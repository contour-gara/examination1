package org.contourgara.examination1.integration;

import java.sql.DriverManager;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
class EmployeesIT {
  private static final String DB_URL = "jdbc:postgresql://localhost:5432/sample";
  private static final String DB_USER = "user";
  private static final String DB_PASSWORD = "password";

  private static final ConnectionHolder connectionHolder =
      () -> DriverManager.getConnection(
          DB_URL,
          DB_USER,
          DB_PASSWORD
      );

  @Test
  @DataSet(value = "datasets/setup/2-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/2-employee.yml")
  void flywayの設定確認() {
    // execute & assert
  }
}
