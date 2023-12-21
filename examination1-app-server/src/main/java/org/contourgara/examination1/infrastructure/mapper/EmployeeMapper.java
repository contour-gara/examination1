package org.contourgara.examination1.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.contourgara.examination1.infrastructure.entity.EmployeeEntity;

import java.util.List;

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
}
