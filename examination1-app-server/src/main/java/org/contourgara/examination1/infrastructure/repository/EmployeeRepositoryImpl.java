package org.contourgara.examination1.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;
import org.contourgara.examination1.infrastructure.exception.QueryExecutionFailException;
import org.contourgara.examination1.infrastructure.mapper.EmployeeMapper;
import org.springframework.stereotype.Repository;

/**
 * 従業員リポジトリの実装クラスです。
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {
  private final EmployeeMapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> findAll() {
    return mapper.findAll().stream().map(EmployeeEntity::convertToModel).toList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Employee> findById(String id) {
    return Optional.ofNullable(mapper.findById(id)).map(EmployeeEntity::convertToModel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long getNextSequence() {
    return mapper.getNextSequence();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Employee create(Employee employee) {
    Integer count = mapper.create(
        new EmployeeEntity(
            employee.employeeId().value(),
            employee.firstName(),
            employee.lastName()
        )
    );

    checkQueryExecution(count, employee.employeeId().value());

    return employee;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(Employee employee) {
    Integer count = mapper.update(
        new EmployeeEntity(
            employee.employeeId().value(),
            employee.firstName(),
            employee.lastName()
        )
    );

    checkQueryExecution(count, employee.employeeId().value());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete(String id) {
    Integer count = mapper.delete(id);

    checkQueryExecution(count, id);
  }

  /**
   * {@inheritDoc}
   */
  private void checkQueryExecution(Integer count, String id) {
    if (count != 1) {
      log.error("クエリが正常に実行できませんでした。[id = {}]", id);
      throw new QueryExecutionFailException("クエリが正常に実行できませんでした。");
    }
  }
}
