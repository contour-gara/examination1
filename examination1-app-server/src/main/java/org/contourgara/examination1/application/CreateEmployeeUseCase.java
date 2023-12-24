package org.contourgara.examination1.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateEmployeeUseCase {
  private final EmployeeRepository repository;

  @Transactional
  public Employee execute(CreateEmployeeParam createEmployeeParam) {
    return repository.create(createEmployeeParam.convertToModel());
  }
}
