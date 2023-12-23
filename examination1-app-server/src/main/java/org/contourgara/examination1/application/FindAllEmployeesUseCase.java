package org.contourgara.examination1.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

/**
 * FindAllEmployeesUseCase は従業員の全件取得を行います。
 */
@RequiredArgsConstructor
@Service
public class FindAllEmployeesUseCase {
  private final EmployeeRepository repository;

  /**
   * 従業員の全件取得を実行します。
   *
   * @return Employee の List。
   */
  public List<Employee> execute() {
    return repository.findAll();
  }
}
