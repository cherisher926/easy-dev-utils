package cn.ifreehub.easy.excel.annotation;

import cn.ifreehub.easy.excel.convert.Convert;
import cn.ifreehub.easy.excel.convert.DefaultConvert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Quding Ding
 * @since 2018/5/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelFiled {
  /**
   * excel header name
   */
  String columnName() default "";

  /**
   * value convert strategy
   */
  Class<? extends Convert> convert() default DefaultConvert.class;

}
