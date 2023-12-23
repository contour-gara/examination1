package org.contourgara.examination1.infrastructure.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;

/**
 * EmployeeMapper は従業員テーブルにアクセスするためのマッパーです。
 */
@Mapper
public interface EmployeeMapper {
  /**
   * 従業員テーブルにあるデータを全件取得します。
   *
   * @return 従業員エンティティの配列。
   */
  @Select("SELECT id, first_name, last_name FROM employees")
  List<EmployeeEntity> findAll();

  /**
   * 従業員テーブルにあるデータから ID で検索します。
   *
   * @param id 検索したい ID。
   * @return 従業員エンティティ。
   */
  @Select("SELECT id, first_name, last_name FROM employees WHERE id = #{id}")
  EmployeeEntity findById(String id);
}
