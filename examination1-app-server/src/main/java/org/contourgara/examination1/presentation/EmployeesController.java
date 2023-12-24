package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.FindAllEmployeesUseCase;
import org.contourgara.examination1.application.FindEmployeeByIdUseCase;
import org.contourgara.examination1.presentation.request.CreateEmployeeRequest;
import org.contourgara.examination1.presentation.response.AllEmployeesResponse;
import org.contourgara.examination1.presentation.response.EmployeeResponse;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 従業員情報のエンドポイントです。
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EmployeesController {
  private final FindAllEmployeesUseCase findAllEmployeesUseCase;
  private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;

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
   * @return AllEmployeesResponse。
   */
  @GetMapping("v1/employees")
  @ResponseStatus(OK)
  public AllEmployeesResponse findAllEmployees() {
    return AllEmployeesResponse.of(findAllEmployeesUseCase.execute());
  }

  /**
   * ID 指定された従業員情報を返します。
   *
   * @param id 取得したい従業員の ID。
   * @return EmployeeResponse。
   */
  @GetMapping("v1/employees/{id}")
  @ResponseStatus(OK)
  public EmployeeResponse findEmployeeById(@PathVariable("id") String id) {
    return EmployeeResponse.of(findEmployeeByIdUseCase.execute(id));
  }

  @PostMapping("v1/employees")
  @ResponseStatus(CREATED)
  public ResponseEntity<Void> createEmployee(CreateEmployeeRequest request) {
    String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();

    URI uri = UriComponentsBuilder.fromUriString(url).path("/" + 3).build().toUri();

    return ResponseEntity.of(ProblemDetail.forStatus(CREATED)).location(uri).build();
  }
}
