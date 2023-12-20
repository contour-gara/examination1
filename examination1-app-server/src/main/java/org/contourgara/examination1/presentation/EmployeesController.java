package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import org.contourgara.examination1.presentation.response.AllEmployeesResponse;
import org.contourgara.examination1.presentation.response.EmployeeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員情報のエンドポイントです。
 */
@RestController
@RequestMapping("/")
public class EmployeesController {
  /**
   * root URL にアクセスされた場合、ステータスコード 200 を返します。
   */
  @GetMapping
  @ResponseStatus(OK)
  public void root() {
    // 何もしません。
  }

  @GetMapping("v1/employees")
  @ResponseStatus(OK)
  public AllEmployeesResponse findAllEmployees() {
    return new AllEmployeesResponse(
      List.of(
        new EmployeeResponse("1", "Taro", "Yamada"),
        new EmployeeResponse("2", "Jiro", "Yamada")
      )
    );
  }
}
