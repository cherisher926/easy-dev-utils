package cn.ifreehub.easy.excel.convert;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * @author Quding Ding
 * @since 2018/5/28
 */
public class DateToStringConvert implements Convert<Date,String> {

  private static FastDateFormat YMDHMS_ = FastDateFormat.getInstance("yyyy-MM-dd hh:MM:ss");

  @Override
  public String apply(Date date) {
    if (date == null) {
      return "";
    }
    return YMDHMS_.format(date);
  }

}
