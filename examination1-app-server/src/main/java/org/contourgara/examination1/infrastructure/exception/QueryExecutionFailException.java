package org.contourgara.examination1.infrastructure.exception;

/**
 * QueryExecutionFailException はクエリの実行結果が予期されない結果だった場合に投げられます。
 */
public class QueryExecutionFailException extends RuntimeException {
  /**
   * QueryExecutionFailException を初期化します。
   *
   * @param message エラーメッセージ。
   */
  public QueryExecutionFailException(String message) {
    super(message);
  }
}
