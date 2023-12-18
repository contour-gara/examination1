package org.contourgara.examination1.presentation;

import static org.springframework.http.HttpStatus.OK;

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
  public void root() {}
}
