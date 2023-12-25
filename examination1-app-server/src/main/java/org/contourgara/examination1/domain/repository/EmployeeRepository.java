package org.contourgara.examination1.domain.repository;

import java.util.List;
import java.util.Optional;
import org.contourgara.examination1.domain.model.Employee;

/**
 * EmployeeRepository は従業員情報の CRUD 操作を行うリポジトリです。
 */
public interface EmployeeRepository {
  /**
   * 従業員の全件取得を行います。
   *
   * @return 従業員モデルのリスト。
   */
  List<Employee> findAll();

  /**
   * ID 検索による従業員取得を行います。
   *
   * @param id 検索したい従業員 ID。
   * @return Employee の Optional。従業員が見つからなかった場合は空の Optional が返ります。
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
   * @param employee 新規登録する従業員。従業員 ID は EmployeeID.createEmptyId() で作成できます。
   * @return 登録された Employee。
   */
  Employee create(Employee employee);
}
