package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.CreateEmployeeUseCase;
import org.contourgara.examination1.application.DeleteEmployeeUseCase;
import org.contourgara.examination1.application.FindAllEmployeesUseCase;
import org.contourgara.examination1.application.FindEmployeeByIdUseCase;
import org.contourgara.examination1.application.UpdateEmployeeUseCase;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.presentation.request.CreateEmployeeRequest;
import org.contourgara.examination1.presentation.request.UpdateEmployeeRequest;
import org.contourgara.examination1.presentation.response.AllEmployeesResponse;
import org.contourgara.examination1.presentation.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 従業員情報のエンドポイントです。
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EmployeesController {
  private final CreateEmployeeUseCase createEmployeeUseCase;
  private final DeleteEmployeeUseCase deleteEmployeeUseCase;
  private final FindAllEmployeesUseCase findAllEmployeesUseCase;
  private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
  private final UpdateEmployeeUseCase updateEmployeeUseCase;

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
   * @return {@link AllEmployeesResponse}。
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
   * @return {@link EmployeeResponse}。
   */
  @GetMapping("v1/employees/{id}")
  @ResponseStatus(OK)
  public EmployeeResponse findEmployeeById(@PathVariable("id") String id) {
    return EmployeeResponse.of(findEmployeeByIdUseCase.execute(id));
  }

  /**
   * 従業員を新規登録します。
   *
   * @param request 新規登録する従業員。名前と名字が必須で、アルファベットのみで 100 文字以内です。
   * @return {@link ResponseEntity}。ヘッダーの Location に登録した従業員情報にアクセスする URL が含まれます。
   */
  @PostMapping("v1/employees")
  @ResponseStatus(CREATED)
  public ResponseEntity<Void> createEmployee(
      @RequestBody @Validated CreateEmployeeRequest request
  ) {
    Employee employee = createEmployeeUseCase.execute(request.convertToParam());

    URI uri = UriComponentsBuilder
        .fromUriString(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString())
        .path("/" + employee.employeeId().value())
        .build()
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  /**
   * 従業員を更新します。
   *
   * @param id 更新する従業員 ID。
   * @param request 更新する従業員。名前と名字は任意で、アルファベットのみで 100 文字以内です。
   */
  @PatchMapping("v1/employees/{id}")
  @ResponseStatus(NO_CONTENT)
  public void updateEmployee(
      @PathVariable("id") String id,
      @RequestBody @Validated UpdateEmployeeRequest request
  ) {
    updateEmployeeUseCase.execute(request.convertToParam(id));
  }

  /**
   * 従業員を削除します。
   *
   * @param id 削除する従業員 ID。
   */
  @DeleteMapping("v1/employees/{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteEmployee(@PathVariable("id") String id) {
    deleteEmployeeUseCase.execute(id);
  }
}
