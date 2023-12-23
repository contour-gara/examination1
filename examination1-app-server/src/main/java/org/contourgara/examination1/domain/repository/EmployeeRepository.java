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
   * @return 従業員モデルのリスト。
   */
  List<Employee> findAll();

  /**
   * ID 検索による従業員取得を行います。
   *
   * @param id 検索したい従業員 ID。
   * @return 従業員モデル。
   */
  Employee findById(String id);
}
