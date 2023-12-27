package org.contourgara.examination1.presentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.contourgara.examination1.TestUtils.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.contourgara.examination1.application.CreateEmployeeUseCase;
import org.contourgara.examination1.application.DeleteEmployeeUseCase;
import org.contourgara.examination1.application.FindAllEmployeesUseCase;
import org.contourgara.examination1.application.FindEmployeeByIdUseCase;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.presentation.request.CreateEmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class EmployeesControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CreateEmployeeUseCase createEmployeeUseCase;

  @MockBean
  DeleteEmployeeUseCase deleteEmployeeUseCase;

  @MockBean
  FindAllEmployeesUseCase findAllEmployeesUseCase;

  @MockBean
  FindEmployeeByIdUseCase findEmployeeByIdUseCase;

  @BeforeEach
  void setUp() {
    RestAssuredMockMvc.mockMvc(mockMvc);
  }

  @Test
  void rootにアクセスできる() {
    // execute & assert
    given()
      .when()
      .get("/")
      .then()
      .status(OK)
      .body(blankOrNullString());
  }

  @Test
  void 全ての従業員が取得できる() {
    // setup
    doReturn(
        List.of(
            new Employee(new EmployeeId("1"), "Taro", "Yamada"),
            new Employee(new EmployeeId("2"), "Jiro", "Yamada")
        )
    ).when(findAllEmployeesUseCase).execute();

    // execute & assert
    given()
        .when()
        .get("/v1/employees")
        .then()
        .status(OK)
        .body("employees[0].id", equalTo("1"))
        .body("employees[0].firstName", equalTo("Taro"))
        .body("employees[0].lastName", equalTo("Yamada"))
        .body("employees[1].id", equalTo("2"))
        .body("employees[1].firstName", equalTo("Jiro"))
        .body("employees[1].lastName", equalTo("Yamada"));
  }

  @Nested
  class ID検索 {
    @Test
    void ID1の従業員が取得できる() {
      // setup
      doReturn(new Employee(new EmployeeId("1"), "Taro", "Yamada"))
          .when(findEmployeeByIdUseCase)
          .execute("1");

      // execute & assert
      given()
          .when()
          .get("/v1/employees/1")
          .then()
          .status(OK)
          .body("id", equalTo("1"))
          .body("firstName", equalTo("Taro"))
          .body("lastName", equalTo("Yamada"));
    }

    @Test
    void ID2の従業員が取得できる() {
      // setup
      doReturn(new Employee(new EmployeeId("2"), "Jiro", "Yamada"))
          .when(findEmployeeByIdUseCase)
          .execute("2");

      // execute & assert
      given()
          .when()
          .get("/v1/employees/2")
          .then()
          .status(OK)
          .body("id", equalTo("2"))
          .body("firstName", equalTo("Jiro"))
          .body("lastName", equalTo("Yamada"));
    }

    @Test
    void 存在しないIDで検索した場合() {
      // setup
      doThrow(new NotFoundEmployeeException("0"))
          .when(findEmployeeByIdUseCase)
          .execute("0");

      // execute & assert
      given()
          .when()
          .get("/v1/employees/0")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0003"))
          .body("message", equalTo("specified employee [id = 0] is not found."))
          .body("details", hasSize(0));
    }
  }

  @Test
  void 従業員を新規登録できる() {
    // setup
    doReturn(new Employee(new EmployeeId("3"), "Hanako", "Shirato"))
        .when(createEmployeeUseCase)
        .execute(new CreateEmployeeParam("Hanako", "Shirato"));

    // execute & assert
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(marshalToJson(new CreateEmployeeRequest("Hanako", "Shirato")))
        .when()
        .post("/v1/employees")
        .then()
        .status(CREATED)
        .header("Location", equalTo("http://localhost/v1/employees/3"));
  }

  @Nested
  class 従業員削除 {
    @Test
    void ID1の従業員を削除できる() {
      // execute & assert
      given()
          .when()
          .delete("/v1/employees/1")
          .then()
          .status(NO_CONTENT);
    }

    @Test
    void ID2の従業員を削除できる() {
      // execute & assert
      given()
          .when()
          .delete("/v1/employees/2")
          .then()
          .status(NO_CONTENT);
    }

    @Test
    void 存在しない従業員を指定した場合() {
      // setup
      doThrow(new NotFoundEmployeeException("0"))
          .when(deleteEmployeeUseCase)
          .execute("0");

      // execute & assert
      given()
          .when()
          .delete("/v1/employees/0")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0003"))
          .body("message", equalTo("specified employee [id = 0] is not found."))
          .body("details", hasSize(0));
    }
  }

  @Nested
  class 新規登録で入力検証が機能しているか {
    @Test
    void 入力されていない場合() {
      // execute & assert
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(marshalToJson(new CreateEmployeeRequest(null, null)))
          .when()
          .post("/v1/employees")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0002"))
          .body("message", equalTo("request validation error is occurred."))
          .body("details", hasSize(2));
    }

    @Test
    void 空文字の場合() {
      // execute & assert
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(marshalToJson(new CreateEmployeeRequest("", "")))
          .when()
          .post("/v1/employees")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0002"))
          .body("message", equalTo("request validation error is occurred."))
          .body("details", hasSize(4));
    }

    @Test
    void アルファベット以外がある場合() {
      // execute & assert
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(marshalToJson(new CreateEmployeeRequest("あ", "あ")))
          .when()
          .post("/v1/employees")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0002"))
          .body("message", equalTo("request validation error is occurred."))
          .body("details", hasSize(2));
    }

    @Test
    void _100文字以上の場合() {
      // execute & assert
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(marshalToJson(new CreateEmployeeRequest("a".repeat(101), "a".repeat(101))))
          .when()
          .post("/v1/employees")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0002"))
          .body("message", equalTo("request validation error is occurred."))
          .body("details", hasSize(2));
    }
  }

  @Nested
  class 新規登録の入力検証で正しいメッセージが返されているか {
    @Test
    void 入力されていない場合() {
      // execute & assert
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(marshalToJson(new CreateEmployeeRequest(null, "lastName")))
          .when()
          .post("/v1/employees")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0002"))
          .body("message", equalTo("request validation error is occurred."))
          .body("details", hasSize(1))
          .body("details[0]", equalTo("firstName must not be blank"));
    }

    @Test
    void アルファベット以外がある場合() {
      // execute & assert
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(marshalToJson(new CreateEmployeeRequest("あ", "lastName")))
          .when()
          .post("/v1/employees")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0002"))
          .body("message", equalTo("request validation error is occurred."))
          .body("details", hasSize(1))
          .body("details[0]", equalTo("firstName must match \"^[a-zA-Z]+$\""));
    }

    @Test
    void _100文字以上の場合() {
      // execute & assert
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(marshalToJson(new CreateEmployeeRequest("a".repeat(101), "lastName")))
          .when()
          .post("/v1/employees")
          .then()
          .status(BAD_REQUEST)
          .body("code", equalTo("0002"))
          .body("message", equalTo("request validation error is occurred."))
          .body("details", hasSize(1))
          .body("details[0]", equalTo("firstName length must be between 0 and 100"));
    }
  }
}
