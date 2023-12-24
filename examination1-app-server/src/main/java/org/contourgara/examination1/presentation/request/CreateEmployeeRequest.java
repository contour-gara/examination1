package org.contourgara.examination1.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.hibernate.validator.constraints.Length;

/**
 * CreateEmployeeRequest は従業員を新規登録する際に用います。
 *
 * @param firstName 新規登録する従業員の名前。
 * @param lastName 新規登録する従業員の名字。
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
   * @return CreateEmployeeParam。
   */
  public CreateEmployeeParam convertToParam() {
    return new CreateEmployeeParam(firstName, lastName);
  }
}
