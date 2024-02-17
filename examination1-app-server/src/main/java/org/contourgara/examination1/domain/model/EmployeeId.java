package org.contourgara.examination1.domain.model;

import static org.apache.commons.lang3.StringUtils.isNumeric;

import org.contourgara.examination1.common.DomainErrorMessage;
import org.contourgara.examination1.domain.exception.DomainValidateException;

/**
 * EmployeeId クラスは従業員 ID を表すモデルです。
 *
 * @param value 従業員 ID。1 以上 999999999 以下の数字で、null であってはなりません。
 */
public record EmployeeId(String value) {
  /**
   * EmployeeId を初期化します。
   *
   * @param value 従業員 ID。1 以上 999999999 以下の数字で、null であってはなりません。
   */
  public EmployeeId {
    if (!isNumeric(value)) {
      throw new DomainValidateException(
          String.format(DomainErrorMessage.EMPLOYEE_ID_VALUE_NUMERIC.getMessage(), value)
      );
    }

    if ((Integer.parseInt(value) <= 0) || (Integer.parseInt(value) >= 1000000000)) {
      throw new DomainValidateException(
          String.format(DomainErrorMessage.EMPLOYEE_ID_VALUE_RANGE.getMessage(), value)
      );
    }
  }

  /**
   * Long 型の数値から EmployeeId を初期化します。
   *
   * @param id 従業員 ID。
   * @return {@link EmployeeId}。
   */
  public static EmployeeId of(Long id) {
    return new EmployeeId(id.toString());
  }
}
