package org.contourgara.examination1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * TestUtils はテストで使用するユーティルクラスです。
 */
@Slf4j
@UtilityClass
public class TestUtils {
  /**
   * オブジェクトを JSON 文字列に変換します。
   *
   * @param obj JSON に変換するオブジェクト。
   * @return JSON 文字列。
   */
  public static String marshalToJson(Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.warn("JSONへの変換に失敗しました。", e);
    }
    return "";
  }
}
