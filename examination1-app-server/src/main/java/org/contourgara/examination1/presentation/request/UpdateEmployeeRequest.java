package org.contourgara.examination1.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.contourgara.examination1.application.param.UpdateEmployeeParam;
import org.hibernate.validator.constraints.Length;

/**
 * UpdateEmployeeRequest は従業員を更新する際に用います。
 *
 * @param firstName 更新する従業員の名前。100 文字のアルファベットです。
 * @param lastName 更新する従業員の名字。100 文字のアルファベットです。
 */
public record UpdateEmployeeRequest(
    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("firstName")
    String firstName,

    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("lastName")
    String lastName
) {
  /**
   * ユースケースに渡すためのパラメータオブジェクトに変換します。
   *
   * @param id 更新する従業員 ID。
   * @return {@link UpdateEmployeeParam}。
   */
  public UpdateEmployeeParam convertToParam(String id) {
    return new UpdateEmployeeParam(id, firstName, lastName);
  }
}
