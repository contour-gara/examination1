package org.contourgara.examination1.application;

import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.model.EmployeeId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateEmployeeUseCase {
  public Employee execute(CreateEmployeeParam createEmployeeParam) {
    return new Employee(new EmployeeId("3"), createEmployeeParam.firstName(), createEmployeeParam.lastName());
  }
}
