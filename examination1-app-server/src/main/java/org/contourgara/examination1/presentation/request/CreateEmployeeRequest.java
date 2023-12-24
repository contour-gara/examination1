package org.contourgara.examination1.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.hibernate.validator.constraints.Length;

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
  public CreateEmployeeParam convertToParam() {
    return new CreateEmployeeParam(firstName, lastName);
  }
}
