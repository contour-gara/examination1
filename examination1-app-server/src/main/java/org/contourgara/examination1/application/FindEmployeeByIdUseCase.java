package org.contourgara.examination1.application;

import java.util.Optional;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.springframework.stereotype.Service;

/**
 * FindEmployeeByIdUseCase は ID 検索による従業員取得を行います。
 */
@Service
public class FindEmployeeByIdUseCase {
  /**
   * ID 検索による従業員取得を実行します。
   *
   * @param id 取得したい従業員の ID。
   * @return 従業員モデル。
   */
  public Employee execute(String id) {
    Optional<Employee> tmp;

    if (id.equals("1")) {
      tmp = Optional.of(new Employee(new EmployeeId("1"), "Taro", "Yamada"));
    } else if (id.equals("2")) {
      tmp = Optional.of(new Employee(new EmployeeId("2"), "Jiro", "Yamada"));
    } else {
      tmp = Optional.empty();
    }

    return tmp.orElseThrow(() -> new NotFoundEmployeeException(id));
  }
}
