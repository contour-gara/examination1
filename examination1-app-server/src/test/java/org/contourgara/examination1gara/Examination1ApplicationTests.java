package org.contourgara.examination1gara;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Examination1ApplicationTests {
  @Autowired
  TempComponent tempComponent;

  @Test
  void contextLoads() {
    assertThat(tempComponent).isNotNull();
  }
}
