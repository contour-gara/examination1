package org.contourgara.examination1.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeResponse(
  @JsonProperty("id") String id,
  @JsonProperty("firstName") String firstName,
  @JsonProperty("lastName") String lastName
) {
}
