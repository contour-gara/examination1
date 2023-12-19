package org.contourgara.examination1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Examination1ApplicationTests {
  @Autowired
  TempComponent tempComponent;

  @Test
  void contextLoads() {
    assertThat(tempComponent).isNotNull();
  }
}
