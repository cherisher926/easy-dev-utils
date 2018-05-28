package cn.ifreehub.easy.excel.helper;

import cn.ifreehub.easy.excel.annotation.ExcelFiled;
import cn.ifreehub.easy.excel.model.ExcelHeader;
import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class BeanHelper {

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
        .map(BeanHelper::toMap)
        .collect(Collectors.toList());
  }

  /**
   * bean to map
   * @param bean bean
   * @return map key is bean filed name,value is the filed value
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


  /**
   * bean to excel header
   * @param bean bean
   * @return excel header
   */
  public static LinkedHashMap<String, ExcelHeader> toHeaderMap(Class bean) {
    // 获取到所有字段
    final Field[] fields = bean.getDeclaredFields();

    return Arrays.stream(fields)
        .map(x -> {
          x.setAccessible(true);
          ExcelFiled annotation = x.getAnnotation(ExcelFiled.class);
          if (null != annotation) {
            return new Pair<>(x.getName(), ExcelHeader.create(annotation.columnName(), ConvertHelper.getConvert(annotation.convert())));
          }
          return new Pair<>(x.getName(), ExcelHeader.create(x.getName()));
        })
        .filter(x -> !Objects.equals(x.getKey(), "this$0"))
        .collect(LinkedHashMap::new, (l, v) -> l.put(v.getKey(), v.getValue()), HashMap::putAll);
  }


}
