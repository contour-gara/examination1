package org.contourgara.examination1.presentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class EmployeesControllerTest {
    @Autowired
    MockMvc mockMvc;

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
                .status(OK);
    }

    @Test
    void 全ての従業員情報が取得できる() {
        // execute & assert
        given()
          .when()
          .get("/v1/employees")
          .then()
          .status(OK);
//          .body("employees[0].id", equalTo("1"))
//          .body("employees[0].firstName", equalTo("Taro"))
//          .body("employees[0].lastName", equalTo("Yamada"))
//          .body("employees[1].id", equalTo("2"))
//          .body("employees[1].firstName", equalTo("Jiro"))
//          .body("employees[1].lastName", equalTo("Yamada"));
    }
}
