package cn.ifreehub.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Quding Ding
 * @since 2017/11/30
 */
public class PropertiesUtil {

  /**
   * 读取classpath下的配置文件
   * @return 该配置文件中键值对
   */
  public static Properties readClasspath(String file) {
    Properties properties = new Properties();
    try (InputStream resource = PropertiesUtil.class.getClassLoader().getResourceAsStream(file)){
      if (null == resource) {
        return properties;
      }
      properties.load(resource);
    } catch (IOException e) {
      // ignore
    }
    return properties;
  }


}
