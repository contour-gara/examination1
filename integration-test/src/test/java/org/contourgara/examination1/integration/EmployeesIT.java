package org.contourgara.examination1.integration;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.OK;

import java.sql.DriverManager;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
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

  @BeforeAll
  static void setUpAll() {
    baseURI = "http://localhost:8080";
  }

  @Test
  @DataSet(value = "datasets/setup/2-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/2-employee.yml")
  void 全件検索した場合従業員情報のリストが返る() {
    // execute & assert
    given()
        .get("/v1/employees")
        .then()
        .statusCode(OK.value())
        .body("employees[0].id", equalTo("1"))
        .body("employees[0].firstName", equalTo("Taro"))
        .body("employees[0].lastName", equalTo("Yamada"))
        .body("employees[1].id", equalTo("2"))
        .body("employees[1].firstName", equalTo("Jiro"))
        .body("employees[1].lastName", equalTo("Yamada"));
  }

  @Test
  @DataSet(value = "datasets/setup/1-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
  void ID検索した場合従業員情報が返る() {
    // execute & assert
    given()
        .get("/v1/employees/1")
        .then()
        .statusCode(OK.value())
        .body("id", equalTo("1"))
        .body("firstName", equalTo("Taro"))
        .body("lastName", equalTo("Yamada"));
  }
}
