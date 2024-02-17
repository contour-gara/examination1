package org.contourgara.examination1.infrastructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * QueryExecutionFailException はクエリの実行結果が予期されない結果だった場合に投げられます。
 */
@Getter
@RequiredArgsConstructor
public class QueryExecutionFailException extends RuntimeException {
  private final String id;
}
