package org.contourgara.examination1.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteEmployeeUseCase {
  private final EmployeeRepository repository;

  public void execute(String id) {
    if (repository.findById(id).isEmpty()) throw new NotFoundEmployeeException(id);

    repository.delete(id);
  }
}
