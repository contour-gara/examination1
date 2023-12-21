package org.contourgara.examination1.application;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.springframework.stereotype.Service;

/**
 * FindAllEmployeesUseCase は従業員の全件取得を行います。
 */
@Service
public class FindAllEmployeesUseCase {
  /**
   * 従業員の全件取得を実行します。
   *
   * @return 従業員モデルのリスト。
   */
  public List<Employee> execute() {
    return List.of(
        new Employee(new EmployeeId("1"), "Taro", "Yamada"),
        new Employee(new EmployeeId("2"), "Jiro", "Yamada")
    );
  }
}
