package org.contourgara.examination1.domain.exception;

import lombok.Getter;

/**
 * ドメインモデルの入力検証に違反した際に投げられる例外です。
 */
@Getter
public class DomainValidateException extends IllegalArgumentException {
  /**
   * DomainValidateException を初期化します。
   *
   * @param message エラーメッセージ。
   */
  public DomainValidateException(String message) {
    super(message);
  }
}
