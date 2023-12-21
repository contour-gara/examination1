package org.contourgara.examination1.domain.repository;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;

/**
 * EmployeeRepository は従業員情報の CRUD 操作を行うリポジトリです。
 */
public interface EmployeeRepository {
  List<Employee> findAll();
}
