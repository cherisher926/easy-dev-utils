package cn.ifreehub.easy.excel.helper;

import com.google.common.collect.Maps;

import cn.ifreehub.easy.excel.convert.Convert;
import cn.ifreehub.easy.excel.exception.ExcelExportException;

import java.util.Map;

/**
 * @author Quding Ding
 * @since 2018/5/28
 */
public class ConvertHelper {

  private static final Map<String, Object> CONVERT_BEAN_MAP = Maps.newHashMap();

  /**
   * get convert, if not exist, new instance
   * @param clazz convert class
   * @return convert instance
   */
  @SuppressWarnings("unchecked")
  public static <T extends Convert> T getConvert(Class<T> clazz) {
    Object bean = CONVERT_BEAN_MAP.get(clazz.getName());
    if (null == bean) {
      synchronized (CONVERT_BEAN_MAP) {
        bean = CONVERT_BEAN_MAP.get(clazz.getName());
        if (null == bean) {
          try {
            bean = clazz.newInstance();
            CONVERT_BEAN_MAP.put(clazz.getName(), bean);
          } catch (InstantiationException | IllegalAccessException e) {
            throw new ExcelExportException(e);
          }
        }
      }
    }
    return (T) bean;
  }

}
