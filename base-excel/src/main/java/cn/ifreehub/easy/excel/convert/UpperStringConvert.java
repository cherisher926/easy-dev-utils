package cn.ifreehub.easy.excel.convert;

/**
 * @author Quding Ding
 * @since 2018/5/28
 */
public class UpperStringConvert implements Convert<String,String> {
  @Override
  public String apply(String s) {
    if (null == s) {
      return null;
    }
    return s.toUpperCase();
  }
}
