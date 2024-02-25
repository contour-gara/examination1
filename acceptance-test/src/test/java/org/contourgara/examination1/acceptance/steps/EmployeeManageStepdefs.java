package org.contourgara.examination1.acceptance.steps;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import org.contourgara.examination1.acceptance.Configuration;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ja.かつ;
import io.cucumber.java.ja.ならば;
import io.cucumber.java.ja.もし;
import io.cucumber.java.ja.前提;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@CucumberContextConfiguration
@SpringBootTest
public class EmployeeManageStepdefs {
  private static final String POST_EMPLOYEE_PATH = "/v1/employees";
  private static final String GET_ALL_EMPLOYEE_PATH = "/v1/employees";
  private static final String GET_EMPLOYEE_PATH = "/v1/employees/%s";
  private static final String UPDATE_EMPLOYEE_PATH = "/v1/employees/%s";
  private static final String DELETE_EMPLOYEE_PATH = "/v1/employees/%s";

  @Autowired
  Configuration configuration;

  @Autowired
  TestContext testContext;

  @前提("適切なBaseURIが指定されている")
  public void 適切なbaseuriが指定されている() {
    String[] schemes = new String[] {"http", "https"};
    UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_LOCAL_URLS);
    boolean actual = urlValidator.isValid(configuration.getBaseUri());
    assertThat(actual).isTrue();
  }

  @もし("ルートURLにアクセスする")
  public void ルートURLにアクセスする() {
    Response response = given().get();
    testContext.setResponse(response);
  }

  @もし("すべての従業員情報を取得する")
  public void すべての従業員情報を取得する() {
    Response response = given().get(GET_ALL_EMPLOYEE_PATH);
    testContext.setResponse(response);
  }

  @もし("従業員情報を登録する")
  public void 従業員情報を登録する(DataTable dataTable) {
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);

    Response response = given()
      .when()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(Map.of(
        "firstName", employees.get(0).get("firstName"),
        "lastName", employees.get(0).get("lastName")
      ))
      .post(POST_EMPLOYEE_PATH);
    testContext.setResponse(response);
  }

  @もし("IDを指定して従業員情報を取得する")
  public void IDを指定して従業員情報を取得する() {
    String id = given().get(GET_ALL_EMPLOYEE_PATH).jsonPath().getString("employees[0].id");
    Response response = given().get(String.format(GET_EMPLOYEE_PATH, id));
    testContext.setResponse(response);
    testContext.setId(id);
  }

  @もし("存在しないIDで従業員情報を取得する")
  public void 存在しないIDで従業員情報を取得する() {
    Response response = given().get(String.format(GET_EMPLOYEE_PATH, "2147483647"));
    testContext.setResponse(response);
    testContext.setId("2147483647");
  }

  @もし("空の従業員情報を登録する")
  public void 空の従業員情報を登録する() {
    Response response = given()
      .when()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(Map.of())
      .post(POST_EMPLOYEE_PATH);
    testContext.setResponse(response);
  }

  @もし("従業員情報を変更する")
  public void 従業員情報を変更する(DataTable dataTable) {
    String id = given().get(GET_ALL_EMPLOYEE_PATH).jsonPath().getString("employees[0].id");
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);

    Response response = given()
      .when()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(Map.of(
        "firstName", employees.get(0).get("firstName"),
        "lastName", employees.get(0).get("lastName")
      ))
      .patch(String.format(UPDATE_EMPLOYEE_PATH, id));
    testContext.setResponse(response);
    testContext.setId(id);
  }

  @もし("存在しないIDで従業員情報を変更する")
  public void 存在しないIDで従業員情報を変更する(DataTable dataTable) {
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);

    Response response = given()
      .when()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(Map.of(
        "firstName", employees.get(0).get("firstName"),
        "lastName", employees.get(0).get("lastName")
      ))
      .patch(String.format(UPDATE_EMPLOYEE_PATH, "2147483647"));

    testContext.setResponse(response);
    testContext.setId("2147483647");
  }


  @もし("IDを指定して従業員情報を削除する")
  public void IDを指定して従業員情報を削除する() {
    String id = given().get(GET_ALL_EMPLOYEE_PATH).jsonPath().getString("employees[0].id");
    Response response = given().delete(String.format(DELETE_EMPLOYEE_PATH, id));
    testContext.setResponse(response);
    testContext.setId(id);
  }

  @もし("存在しないIDで従業員情報を削除する")
  public void 存在しないIDで従業員情報を削除する() {
    Response response = given().delete(String.format(DELETE_EMPLOYEE_PATH, "2147483647"));
    testContext.setResponse(response);
    testContext.setId("2147483647");
  }

  @ならば("HTTPステータスコードとして{int}が返却される")
  public void HTTPステータスコードとしてintが返却される(int statusCode) {
    Response response = testContext.getResponse();
    response.then().statusCode(statusCode);
  }

  @かつ("ContentTypeとして{string}が返却される")
  public void ContentTypeとしてstringが返却される(String contentType) {
    Response response = testContext.getResponse();
    response.then().contentType(contentType);
  }

  @かつ("Locationとして登録した従業員情報にアクセスするURLが返却される")
  public void Locationとして登録した従業員情報にアクセスするURLが返却される() {
    Response response = testContext.getResponse();
    response.then().header("Location", matchesPattern(configuration.getBaseUri() + POST_EMPLOYEE_PATH + "/\\d+"));
  }

  @かつ("空のBodyが返却される")
  public void 空のBodyが返却される() {
    Response response = testContext.getResponse();
    response.then().body(emptyString());
  }

  @かつ("[ID、名字、名前]がオブジェクトで返却される")
  public void id_名字_名前がオブジェクトで返却される() {
    Response response = testContext.getResponse();
    response.then().assertThat().body(matchesJsonSchemaInClasspath("get-employee-schema.json"));
  }

  @かつ("[ID、名字、名前]がリストで返却される")
  public void id_名字_名前がリストで返却される() {
    Response response = testContext.getResponse();
    response.then().assertThat().body(matchesJsonSchemaInClasspath("get-all-employees-schema.json"));
  }

  @かつ("従業員情報が変更されている")
  public void 従業員情報が変更されている(DataTable dataTable) {
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);
    String id = testContext.getId();

    given()
      .get(String.format(GET_EMPLOYEE_PATH, id))
      .then()
      .statusCode(HttpStatus.OK.value())
      .body("id", equalTo(id))
      .body("firstName", equalTo(employees.get(0).get("firstName")))
      .body("lastName", equalTo(employees.get(0).get("lastName")));
  }

  @かつ("従業員情報が削除されている")
  public void 従業員情報が削除されている() {
    String id = testContext.getId();

    given()
      .delete(String.format(GET_EMPLOYEE_PATH, id))
      .then()
      .statusCode(HttpStatus.BAD_REQUEST.value())
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body("code", equalTo("0003"))
      .body("message", equalTo(String.format("specified employee [id = %s] is not found.", id)))
      .body("details", empty());
  }

  @かつ("codeとして{string}が返却される")
  public void codeとしてstringが返却される(String code) {
    Response response = testContext.getResponse();
    response.then().body("code", equalTo(code));
  }

  @かつ("messageとして{string}が返却される")
  public void messageとしてstringが返却される(String message) {
    Response response = testContext.getResponse();

    if (isNull(testContext.getId())) {
      response.then().body("message", equalTo(message));
    } else {
      response.then().body("message", equalTo(String.format(message, testContext.getId())));
    }
  }

  @かつ("detailsとして空のリストが返却される")
  public void detailsとして空のリストが返却される() {
    Response response = testContext.getResponse();
    response.then().body("details", empty());
  }

  @かつ("detailsとして詳細なエラー内容を含むリストが返却される")
  public void detailsとして詳細なエラー内容を含むリストが返却される() {
    Response response = testContext.getResponse();
    response.then().body("details", not(empty()));
  }
}
