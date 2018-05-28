package cn.ifreehub.easy.excel.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.ifreehub.easy.excel.exception.ExcelReadException;
import cn.ifreehub.easy.excel.model.ExcelFileType;
import cn.ifreehub.easy.excel.helper.ExcelHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Quding Ding
 * @since 2018/3/7
 */
public class ExcelReadBuilder {

  private Workbook workbook;

  private ExcelReadBuilder(Workbook workbook) {
    this.workbook = workbook;
  }

  /**
   * 指定文件地址
   *
   * @param path 地址
   * @return 该实例
   */
  public static ExcelReadBuilder from(String path) {
    if (StringUtils.isEmpty(path)) {
      throw new IllegalArgumentException("file path is empty");
    }
    ExcelFileType type = StringUtils.endsWithIgnoreCase(path, ExcelFileType.XLS.name()) ? ExcelFileType.XLS : ExcelFileType.XLSX;
    try {
      FileInputStream inputStream = new FileInputStream(new File(path));
      if (type == ExcelFileType.XLS) {
        return new ExcelReadBuilder(new HSSFWorkbook(inputStream));
      }
      return new ExcelReadBuilder(new XSSFWorkbook(inputStream));
    } catch (IOException e) {
      throw new ExcelReadException(e);
    }
  }

  /**
   * 解析函数
   */
  private List<Map<String, Object>> resolve() {
    List<Map<String, Object>> result = Lists.newArrayList();

    final Sheet sheet = workbook.getSheetAt(0);
    final List<String> headers = getHeaders(sheet);

    final Iterator<Row> rowIterator = sheet.rowIterator();
    for (int j = 0; rowIterator.hasNext(); j++) {
      // 处理每一行
      final Row next = rowIterator.next();
      // 过滤指定行数
      if (j < 1) {
        continue;
      }
      Map<String, Object> tempObj = Maps.newHashMap();
      next.cellIterator().forEachRemaining(x -> tempObj.put(headers.get(x.getColumnIndex()), ExcelHelper.getValueFromCell(x)));
      result.add(tempObj);
    }
    close();
    return result;
  }

  /**
   * 关闭资源
   */
  private void close() {
    try {
      workbook.close();
    } catch (IOException e) {
      throw new ExcelReadException(e);
    }
  }

  /**
   * 获取到表头
   * @param sheet 当前表
   * @return 表头,有序
   */
  private List<String> getHeaders(Sheet sheet) {
    final Row row = sheet.getRow(0);
    List<String> headers = Lists.newArrayList();
    row.cellIterator().forEachRemaining(x -> headers.add(x.getStringCellValue()));
    return headers;
  }

  public List<Map<String, Object>> toMap() {
    return resolve();
  }

}
