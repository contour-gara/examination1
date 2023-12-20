package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.FindAllEmployeesUseCase;
import org.contourgara.examination1.presentation.response.AllEmployeesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員情報のエンドポイントです。
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EmployeesController {
  private final FindAllEmployeesUseCase findAllEmployeesUseCase;
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
    return AllEmployeesResponse.of(findAllEmployeesUseCase.execute());
  }
}
