package org.contourgara.examination1.presentation.request;

import org.contourgara.examination1.application.param.CreateEmployeeParam;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CreateEmployeeRequestTest {
  @Test
  void パラムに変換できる() {
    // setup
    CreateEmployeeRequest sut = new CreateEmployeeRequest("Hanako", "Shirato");

    // execute
    CreateEmployeeParam actual = sut.convertToParam();

    // assert
    CreateEmployeeParam expected = new CreateEmployeeParam("Hanako", "Shirato");

    assertThat(actual).isEqualTo(expected);
  }
}
