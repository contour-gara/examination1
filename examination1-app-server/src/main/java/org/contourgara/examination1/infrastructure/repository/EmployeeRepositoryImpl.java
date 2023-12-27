package org.contourgara.examination1.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.domain.repository.EmployeeRepository;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;
import org.contourgara.examination1.infrastructure.mapper.EmployeeMapper;
import org.springframework.stereotype.Repository;

/**
 * 従業員リポジトリの実装クラスです。
 */
@RequiredArgsConstructor
@Repository
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
    // TODO: 戻り値をうけとり、作成できたかどうか確認
    mapper.create(
        new EmployeeEntity(
            employee.employeeId().value(),
            employee.firstName(),
            employee.lastName()
        )
    );

    return employee;
  }
}
