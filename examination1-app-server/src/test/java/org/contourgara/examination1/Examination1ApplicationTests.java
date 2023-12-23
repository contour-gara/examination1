package org.contourgara.examination1;

import static org.assertj.core.api.Assertions.assertThat;

import org.contourgara.examination1.presentation.EmployeesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Examination1ApplicationTests {
  @Autowired
  EmployeesController employeesController;

  @Test
  void contextLoads() {
    assertThat(employeesController).isNotNull();
  }
}
