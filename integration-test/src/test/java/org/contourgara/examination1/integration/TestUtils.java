package org.contourgara.examination1.integration;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

@UtilityClass
public class TestUtils {
  public static String readFrom(String path) throws IOException {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource("classpath:" + path);
    Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8);
    return FileCopyUtils.copyToString(reader);
  }
}
