package org.contourgara.examination1.domain.repository;

import java.util.List;
import java.util.Optional;
import org.contourgara.examination1.domain.model.Employee;
import org.contourgara.examination1.infrastructure.exception.QueryExecutionFailException;

/**
 * EmployeeRepository は従業員情報の CRUD 操作を行うリポジトリです。
 */
public interface EmployeeRepository {
  /**
   * 従業員の全件取得を行います。
   *
   * @return {@link Employee} のリスト。
   */
  List<Employee> findAll();

  /**
   * ID 検索による従業員取得を行います。
   *
   * @param id 検索したい従業員 ID。
   * @return {@link Employee} の Optional。従業員が見つからなかった場合は空の Optional が返ります。
   */
  Optional<Employee> findById(String id);

  /**
   * 次のシーケンスを取得し、シーケンスを更新します。
   *
   * @return 次のシーケンス。
   */
  Long getNextSequence();

  /**
   * 従業員の新規登録を行います。
   *
   * @param employee 新規登録する従業員。
   * @return 登録された {@link Employee}。
   * @throws QueryExecutionFailException クエリの実行に失敗した際に投げられます。
   */
  Employee create(Employee employee);

  /**
   * 従業員の更新を行います。
   *
   * @param employee 更新する従業員。
   * @throws QueryExecutionFailException クエリの実行に失敗した際に投げられます。
   */
  void update(Employee employee);

  /**
   * 従業員の削除を行います。
   *
   * @param id 削除する従業員 ID。
   * @throws QueryExecutionFailException クエリの実行に失敗した際に投げられます。
   */
  void delete(String id);
}
