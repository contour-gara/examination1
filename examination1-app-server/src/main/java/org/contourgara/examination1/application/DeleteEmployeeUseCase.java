package org.contourgara.examination1.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 従業員の削除を行います。
 */
@RequiredArgsConstructor
@Service
public class DeleteEmployeeUseCase {
  private final EmployeeRepository repository;

  /**
   * 従業員の削除を実行します。
   *
   * @param id 削除する従業員 ID。
   * @throws NotFoundEmployeeException 従業員が見つからなかった場合に投げられます。
   */
  @Transactional
  public void execute(String id) {
    if (repository.findById(id).isEmpty()) throw new NotFoundEmployeeException(id);

    repository.delete(id);
  }
}
