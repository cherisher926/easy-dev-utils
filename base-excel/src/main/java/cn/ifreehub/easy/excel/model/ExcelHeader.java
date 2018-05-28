package cn.ifreehub.easy.excel.model;

import lombok.Data;

import cn.ifreehub.easy.excel.convert.Convert;
import cn.ifreehub.easy.excel.helper.BeanHelper;
import cn.ifreehub.easy.excel.helper.ConvertHelper;
import cn.ifreehub.easy.util.Assert;

import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * excel header config
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

  public static ExcelHeader create(String displayHeader,Class<? extends Convert> convert){
    return new ExcelHeader(displayHeader, ConvertHelper.getConvert(convert));
  }

  public static ExcelHeader create(String displayHeader,Convert convert){
    return new ExcelHeader(displayHeader, convert);
  }

// -------------builder模式方便用户使用

  public static ExcelHeaderBuilder builder() {
    return new ExcelHeaderBuilder();
  }

  public static class ExcelHeaderBuilder {
    LinkedHashMap<String, ExcelHeader> headers = new LinkedHashMap<>();


    public ExcelHeaderBuilder withHeader(String originHeader,String displatHeader) {
      headers.put(originHeader, ExcelHeader.create(displatHeader));
      return this;
    }

    public ExcelHeaderBuilder withHeader(String originHeader,String displatHeader, Class<Convert> convert) {
      headers.put(originHeader, ExcelHeader.create(displatHeader, convert));
      return this;
    }

    public ExcelHeaderBuilder withHeader(String originHeader,ExcelHeader displatHeader) {
      headers.put(originHeader, displatHeader);
      return this;
    }

    public ExcelHeaderBuilder withHeader(Class bean) {
      headers.putAll(BeanHelper.toHeaderMap(bean));
      return this;
    }

    public LinkedHashMap<String, ExcelHeader> build() {
      Assert.state(!headers.isEmpty(), "no displayHeaders");
      return headers;
    }

  }

}
