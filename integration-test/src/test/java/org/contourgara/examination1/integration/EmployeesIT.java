package org.contourgara.examination1.integration;

import static io.restassured.RestAssured.*;
import static org.contourgara.examination1.integration.TestUtils.readFrom;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
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
import org.springframework.http.MediaType;

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

  @Test
  @DataSet(value = "datasets/setup/empty-table.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
  void 登録した場合ヘッダーとしてロケーションが返る() throws Exception {
    // execute & assert
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(readFrom("create.json"))
        .when()
        .post("/v1/employees")
        .then()
        .statusCode(CREATED.value())
        .header("Location", equalTo("http://localhost:8080/v1/employees/1"));
  }

  @Test
  @DataSet(value = "datasets/setup/1-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee-update.yml")
  void 更新した場合空のbodyが返る() throws Exception {
    // execute & assert
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(readFrom("update.json"))
        .when()
        .patch("/v1/employees/1")
        .then()
        .statusCode(NO_CONTENT.value())
        .body(equalTo(""));
  }

  @Test
  @DataSet(value = "datasets/setup/2-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
  void 削除した場合空のbodyが返る() {
    // execute & assert
    given()
        .when()
        .delete("/v1/employees/2")
        .then()
        .statusCode(NO_CONTENT.value())
        .body(equalTo(""));
  }

  @Test
  @DataSet(value = "datasets/setup/1-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
  void ID検索で存在しないIDを指定した場合エラーレスポンスが返る() {
    // execute & assert
    given()
        .when()
        .get("/v1/employees/2")
        .then()
        .statusCode(BAD_REQUEST.value())
        .body("code", equalTo("0003"))
        .body("message", equalTo("specified employee [id = 2] is not found."))
        .body("details", hasSize(0));
  }

  @Test
  @DataSet(value = "datasets/setup/1-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
  void 登録で入力規制に違反した場合エラーレスポンスが返る() throws Exception {
    // execute & assert
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(readFrom("create-bad-request.json"))
        .when()
        .post("/v1/employees")
        .then()
        .statusCode(BAD_REQUEST.value())
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details[0]", equalTo("firstName must not be blank"));
  }

  @Test
  @DataSet(value = "datasets/setup/1-employee.yml")
  @ExpectedDataSet(value = "datasets/expected/1-employee.yml")
  void 更新で入力規制に違反した場合エラーレスポンスが返る() throws Exception {
    // execute & assert
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(readFrom("update-bad-request.json"))
        .when()
        .patch("/v1/employees/1")
        .then()
        .statusCode(BAD_REQUEST.value())
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details[0]", equalTo("lastName must match \"^[a-zA-Z]+$\""));
  }
}
