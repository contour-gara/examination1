package org.contourgara.examination1.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.contourgara.examination1.application.param.UpdateEmployeeParam;
import org.hibernate.validator.constraints.Length;

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
  public UpdateEmployeeParam convertToParam(String id) {
    return new UpdateEmployeeParam(id, firstName, lastName);
  }
}
