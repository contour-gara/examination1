package org.contourgara.examination1.presentation;

import static java.util.Collections.emptyList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.contourgara.examination1.application.exception.NotFoundEmployeeException;
import org.contourgara.examination1.presentation.response.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler は例外を検知し適切なエラーレスポンスを返すクラスです。
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  /**
   * ID 検索した際に従業員が見つからない場合にこのメソッドが実行されます。
   * レスポンスステータスは BAD_REQUEST です。
   *
   * @param e 従業員が見つからなかった時の例外。
   * @return ErrorResponse。レスポンスボディになります。code は 0003 で、message には見つからなかった従業員 ID が含まれます。
   */
  @ExceptionHandler(NotFoundEmployeeException.class)
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleNotFoundEmployeeException(NotFoundEmployeeException e) {
    log.warn("指定された ID の従業員が見つかりません。[id = {}]", e.getId(), e);
    return new ErrorResponse(
        "0003",
        String.format("specified employee [id = %s] is not found.", e.getId()),
        emptyList()
    );
  }

  /**
   * リクエストに入力違反があった場合にこのメソッドが実行されます。
   * レスポンスステータスは BAD_REQUEST です。
   *
   * @param e リクエストに入力違反があった時の例外。
   * @return ErrorResponse。レスポンスボディになります。code は 0002 で、details には入力違反の内容が含まれます。
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<String> details = e.getFieldErrors().stream()
        .map(error -> {
          log.warn(
              "入力エラーが発生しました。[{} = {}: {}]",
              error.getField(),
              error.getRejectedValue(),
              error.getDefaultMessage()
          );

          return String.format("%s %s", error.getField(), error.getDefaultMessage());
        })
        .toList();

    return new ErrorResponse(
        "0002",
        "request validation error is occurred.",
        details
    );
  }

  // TODO: 汎用的なハンドラーを作る
}
