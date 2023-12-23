package org.contourgara.examination1.application.exception;

import lombok.Getter;

/**
 * NotFoundEmployeeException は従業員が見つからなかった場合に投げます。
 */
@Getter
public class NotFoundEmployeeException extends RuntimeException {
  /**
   * 見つからなかった従業員 ID。
   */
  private final String id;

  /**
   * NotFoundEmployeeException を初期化します。
   *
   * @param id 見つからなかった従業員 ID。
   */
  public NotFoundEmployeeException(String id) {
    super(String.format("id = %s", id));
    this.id = id;
  }
}
