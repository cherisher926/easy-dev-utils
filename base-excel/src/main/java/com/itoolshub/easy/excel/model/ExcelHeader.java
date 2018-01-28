package com.itoolshub.easy.excel.model;

import lombok.Data;

import java.util.function.Function;

/**
 * @author Quding Ding
 * @since 2017/12/1
 */
@Data
public class ExcelHeader {
  /**
   * 展示名称
   */
  private String displayHeader;
  /**
   * 对应数据转换器
   */
  private Function convert;


  private ExcelHeader(String displayHeader,Function convert){
    this.displayHeader = displayHeader;
    this.convert = convert;
  }

  public static ExcelHeader create(String displayHeader){
    return new ExcelHeader(displayHeader, Function.identity());
  }

  public static ExcelHeader create(String displayHeader,Function convert){
    return new ExcelHeader(displayHeader, convert);
  }

}
