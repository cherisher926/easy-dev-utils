package cn.ifreehub.easy.excel.util;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Quding Ding
 * @since 2018/3/7
 */
public class ExcelUtil {

  /**
   * 转换一个cell中的值
   * @return cell中的值
   */
  public static Object getValueFromCell(Cell cell) {
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_NUMERIC:
        double number = cell.getNumericCellValue();
        long flooredNumber = (long) Math.floor(number);
        if (number > flooredNumber) {
          return number;
        } else {
          return flooredNumber;
        }
      case Cell.CELL_TYPE_STRING:
        return cell.getStringCellValue();
      case Cell.CELL_TYPE_BOOLEAN:
        return cell.getBooleanCellValue();
      default:
        return "";
    }
  }
}
