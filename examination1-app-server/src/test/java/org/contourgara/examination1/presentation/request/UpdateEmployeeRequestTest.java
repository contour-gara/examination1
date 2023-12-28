package org.contourgara.examination1.presentation.request;

import static org.assertj.core.api.Assertions.*;

import org.contourgara.examination1.application.param.UpdateEmployeeParam;
import org.junit.jupiter.api.Test;

class UpdateEmployeeRequestTest {
  @Test
  void ユースケースに渡すオブジェクトに変換できる() {
    // setup
    UpdateEmployeeRequest sut = new UpdateEmployeeRequest(null, "Yamamoto");

    // execute
    UpdateEmployeeParam actual = sut.convertToParam("1");

    // assert
    UpdateEmployeeParam expected = new UpdateEmployeeParam("1", null, "Yamamoto");

    assertThat(actual).isEqualTo(expected);
  }
}
