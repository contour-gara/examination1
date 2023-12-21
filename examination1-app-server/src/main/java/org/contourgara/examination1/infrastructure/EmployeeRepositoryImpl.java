package org.contourgara.examination1.infrastructure;

import java.util.List;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

/**
 * 従業員リポジトリの実装クラスです。
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> findAll() {
    return List.of(
        new Employee(new EmployeeId("1"), "Taro", "Yamada"),
        new Employee(new EmployeeId("2"), "Jiro", "Yamada")
    );
  }
}
