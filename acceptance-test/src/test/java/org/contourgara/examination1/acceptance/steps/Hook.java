package org.contourgara.examination1.acceptance.steps;

import org.contourgara.examination1.acceptance.Configuration;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;

public class Hook {
  @Autowired
  Configuration configuration;

  @Before
  public void setRestAssured() {
    RestAssured.baseURI = configuration.getBaseUri();
  }
}
