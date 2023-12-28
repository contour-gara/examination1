package org.contourgara.examination1.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.application.param.UpdateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 従業員の更新を行います。
 */
@RequiredArgsConstructor
@Service
public class UpdateEmployeeUseCase {
  private final EmployeeRepository repository;

  /**
   * 従業員の更新を実行します。
   *
   * @param param 更新する従業員情報。
   * @throws NotFoundEmployeeException 従業員が見つからなかった場合に投げられます。
   */
  @Transactional
  public void execute(UpdateEmployeeParam param) {
    Employee employee = repository.findById(param.id())
        .orElseThrow(() -> new NotFoundEmployeeException(param.id()));

    repository.update(param.convertToModel(employee));
  }
}
