package org.contourgara.examination1.presentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.contourgara.examination1.TestUtils.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.contourgara.examination1.application.CreateEmployeeUseCase;
import org.contourgara.examination1.application.DeleteEmployeeUseCase;
import org.contourgara.examination1.application.FindAllEmployeesUseCase;
import org.contourgara.examination1.application.FindEmployeeByIdUseCase;
import org.contourgara.examination1.application.UpdateEmployeeUseCase;
import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.presentation.request.CreateEmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

  @MockBean
  UpdateEmployeeUseCase updateEmployeeUseCase;

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

  @ParameterizedTest
  @CsvSource(textBlock = """
      1,Taro,Yamada
      2,Jiro,Yamada
      """)
  void ID検索で従業員が取得できる(String id, String firstName, String lastName) {
    // setup
    doReturn(new Employee(new EmployeeId(id), firstName, lastName))
        .when(findEmployeeByIdUseCase)
        .execute(id);

    // assert & execute
    given()
        .when()
        .get("/v1/employees/" + id)
        .then()
        .status(OK)
        .body("id", equalTo(id))
        .body("firstName", equalTo(firstName))
        .body("lastName", equalTo(lastName));
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

  @ParameterizedTest
  @CsvSource(textBlock = """
      1
      2
      """)
  void 従業員を更新できる(String id) {
    // execute & assert
    given()
        .when()
        .patch("/v1/employees/" + id)
        .then()
        .status(NO_CONTENT)
        .body(equalTo(""));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      1
      2
      """)
  void 従業員を削除できる(String id) {
    // execute & assert
    given()
        .when()
        .delete("/v1/employees/" + id)
        .then()
        .status(NO_CONTENT)
        .body(equalTo(""));
  }
}
