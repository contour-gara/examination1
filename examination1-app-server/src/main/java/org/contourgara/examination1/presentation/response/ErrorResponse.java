package org.contourgara.examination1.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * ErrorResponse はアプリケーション内部で例外が発生した場合に返すレスポンスです。
 *
 * @param code エラーコード。バリデーションエラーの場合は "0002"、従業員が見つからない場合は "0003" です。
 * @param message エラーメッセージ。どのようなエラーかが説明されます。
 * @param details エラーの詳細。バリデーションエラーの場合、エラー内容が入ります。
 */
public record ErrorResponse(
    @JsonProperty("code") String code,
    @JsonProperty("message") String message,
    @JsonProperty("details") List<String> details
) {
}
