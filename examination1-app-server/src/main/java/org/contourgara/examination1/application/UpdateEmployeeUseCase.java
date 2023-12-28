package org.contourgara.examination1.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.application.param.UpdateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateEmployeeUseCase {
  private final EmployeeRepository repository;

  public void execute(UpdateEmployeeParam updateEmployeeParam) {
    Employee employee = repository.findById(updateEmployeeParam.id())
        .orElseThrow(() -> new NotFoundEmployeeException(updateEmployeeParam.id()));
  }
}
