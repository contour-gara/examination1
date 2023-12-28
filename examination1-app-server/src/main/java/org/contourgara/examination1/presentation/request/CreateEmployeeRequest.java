package org.contourgara.examination1.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.hibernate.validator.constraints.Length;

/**
 * CreateEmployeeRequest は従業員を新規登録する際に用います。
 *
 * @param firstName 新規登録する従業員の名前。入力は必須で 100 文字のアルファベットです。
 * @param lastName 新規登録する従業員の名字。入力は必須で 100 文字のアルファベットです。
 */
public record CreateEmployeeRequest(
    @Length(max = 100)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("firstName")
    String firstName,

    @Length(max = 100)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("lastName")
    String lastName
) {
  /**
   * ユースケースに渡すためのパラメータオブジェクトに変換します。
   *
   * @return {@link CreateEmployeeParam}。
   */
  public CreateEmployeeParam convertToParam() {
    return new CreateEmployeeParam(firstName, lastName);
  }
}
