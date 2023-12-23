package org.contourgara.examination1.domain.repository;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;

/**
 * EmployeeRepository は従業員情報の CRUD 操作を行うリポジトリです。
 */
public interface EmployeeRepository {
  /**
   * 従業員の全件取得を行います。
   *
   * @return 全従業員情報。
   */
  List<Employee> findAll();
}
