package org.contourgara.examination1.domain.model;

import static org.apache.commons.lang3.StringUtils.isNumeric;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * EmployeeId クラスは従業員 ID を表すモデルです。
 */
@EqualsAndHashCode
@Getter
public class EmployeeId {
  /**
   * 従業員 ID。
   */
  private final String value;

  /**
   * EmployeeId を初期化します。
   *
   * @param value 従業員 ID。1 以上 999999999 以下の数字で、null であってはなりません。
   */
  public EmployeeId(String value) {
    if (!isNumeric(value)) throw new IllegalArgumentException("従業員 ID は数字です。");
    if ((Integer.parseInt(value) <= 0) || (Integer.parseInt(value) >= 1000000000)) {
      throw new IllegalArgumentException("従業員 ID は 1 以上 999999999 以下です。");
    }

    this.value = value;
  }

  /**
   * 従業員 ID が空文字の EmployeeId が作成されます。
   */
  private EmployeeId() {
    this.value = "";
  }

  /**
   * 従業員 ID が空文字の EmployeeId が作成されます。
   *
   * @return ID が空文字の EmployeeId。
   */
  public static EmployeeId of(Long id) {
    return new EmployeeId(id.toString());
  }
}
