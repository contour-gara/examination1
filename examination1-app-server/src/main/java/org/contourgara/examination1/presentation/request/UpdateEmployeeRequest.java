package org.contourgara.examination1.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.contourgara.examination1.application.param.UpdateEmployeeParam;

public record UpdateEmployeeRequest(
    @JsonProperty("firstName")
    String firstName,

    @JsonProperty("lastName")
    String lastName
) {
  public UpdateEmployeeParam convertToParam(String id) {
    return new UpdateEmployeeParam(id, firstName, lastName);
  }
}
