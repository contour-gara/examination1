package org.contourgara.examination1.presentation.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
