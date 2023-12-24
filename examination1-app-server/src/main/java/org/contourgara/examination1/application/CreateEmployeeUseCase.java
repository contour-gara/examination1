package org.contourgara.examination1.application;

import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.contourgara.examination1.domain.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateEmployeeUseCase {
  public Employee execute(CreateEmployeeParam createEmployeeParam) {
    return createEmployeeParam.convertToModel("3");
  }
}
