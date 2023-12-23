package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.FindAllEmployeesUseCase;
import org.contourgara.examination1.presentation.response.AllEmployeesResponse;
import org.contourgara.examination1.presentation.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  /**
   * 全ての従業員情報を返します。
   *
   * @return 全ての従業員の JSON。
   */
  @GetMapping("v1/employees")
  @ResponseStatus(OK)
  public AllEmployeesResponse findAllEmployees() {
    return AllEmployeesResponse.of(findAllEmployeesUseCase.execute());
  }

  /**
   * ID 指定された従業員情報を返します。
   *
   * @param id 検索したい従業員の ID
   */
  @GetMapping("v1/employees/{id}")
  @ResponseStatus(OK)
  public EmployeeResponse findEmployeeById(@PathVariable("id") String id) {
    if (id.equals("1")) return new EmployeeResponse("1", "Taro", "Yamada");
    if (id.equals("2")) return new EmployeeResponse("2", "Jiro", "Yamada");
    return null;
  }
}
