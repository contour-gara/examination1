package org.contourgara.examination1.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.application.param.UpdateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateEmployeeUseCase {
  private final EmployeeRepository repository;

  @Transactional
  public void execute(UpdateEmployeeParam param) {
    Employee employee = repository.findById(param.id())
        .orElseThrow(() -> new NotFoundEmployeeException(param.id()));

    repository.update(param.convertToModel(employee));
  }
}
