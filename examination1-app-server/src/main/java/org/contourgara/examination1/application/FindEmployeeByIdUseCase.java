package org.contourgara.examination1.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

/**
 * FindEmployeeByIdUseCase は ID 検索による従業員取得を行います。
 */
@RequiredArgsConstructor
@Service
public class FindEmployeeByIdUseCase {
  private final EmployeeRepository repository;

  /**
   * ID 検索による従業員取得を実行します。
   *
   * @param id 取得したい従業員の ID。
   * @return Employee。
   * @throws NotFoundEmployeeException 従業員が見つからなかった場合に投げられます。
   */
  public Employee execute(String id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundEmployeeException(id));
  }
}
