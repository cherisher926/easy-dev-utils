package cn.ifreehub.easy.excel.util;

import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * convert bean to maps
 *
 * @author Quding Ding
 * @since 2017/12/1
 */
public class MapUtils {

  /**
   * bean转Map工具
   *
   * @param beans bean集合
   * @return map集合
   */
  public static <T> List<Map<String, Object>> toListMap(List<T> beans) {
    if (null == beans || beans.isEmpty()) {
      return Collections.emptyList();
    }
    return beans.parallelStream()
        .map(MapUtils::toMap)
        .collect(Collectors.toList());
  }

  /**
   * bean转map
   */
  public static <T> Map<String, Object> toMap(T bean) {
    // 获取到所有字段
    final Class<?> beanClass = bean.getClass();
    final Field[] fields = beanClass.getDeclaredFields();
    return Arrays.stream(fields)
        .map(x -> {
          x.setAccessible(true);
          try {
            return new Pair<>(x.getName(), x.get(bean));
          } catch (IllegalAccessException e) {
            // do nothing
          }
          return null;
        })
        .filter(x -> x!= null && !Objects.equals(x.getKey(), "this$0"))
        .collect(Collectors.toMap(Pair::getKey, Pair::getValue,(v1,v2) -> v1));

  }
}
