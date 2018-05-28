package cn.ifreehub.easy.excel.core;


import cn.ifreehub.easy.excel.exception.ExcelExportException;
import cn.ifreehub.easy.excel.model.ExcelFileType;
import cn.ifreehub.easy.excel.model.ExcelHeader;
import cn.ifreehub.easy.excel.helper.BeanHelper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Quding Ding
 * @since 2017/11/30
 */
public class ExcelExportBuilder implements Closeable {
  /**
   * 导出内容的数据源
   */
  private List<Map<String, Object>> mapData;
  /**
   * 对应的excel表单
   */
  private Workbook workbook;
  /**
   * 针对header的转换器
   */
  private LinkedHashMap<String, ExcelHeader> headerAndConvert;

  private static int CHINESE_CHARACTER_WIDTH = 512;
  /**
   * 针对3w以上的数据量+XSS则使用SXSSFWorkbook导出
   */
  private static final Integer BIG_TABLE = 30000;

  // --------------datasource------------

  private ExcelExportBuilder(List<Map<String, Object>> mapData) {
    this.mapData = mapData;
  }

  public static ExcelExportBuilder fromMap(List<Map<String, Object>> data) {
    return new ExcelExportBuilder(data);
  }

  public static ExcelExportBuilder fromBean(List<?> data) {
    return new ExcelExportBuilder(BeanHelper.toListMap(data));
  }

  /**
   * and操作相当于清空数据,附加上新sheet
   *
   * @param data 数据源
   * @return 该实例
   */
  public ExcelExportBuilder andFormMap(List<Map<String, Object>> data) {
    this.mapData = data;
    return this;
  }

  /**
   * and操作相当于清空数据,附加上新sheet
   *
   * @param data 数据源
   * @return 该实例
   */
  public ExcelExportBuilder andFormBean(List<?> data) {
    this.mapData = BeanHelper.toListMap(data);
    return this;
  }

  // --------------header------------

  public ExcelExportBuilder displayHeader(LinkedHashMap<String, ExcelHeader> headerAndConvert) {
    this.headerAndConvert = headerAndConvert;
    return this;
  }

  public ExcelExportBuilder excelType(ExcelFileType type) {
    if (null != workbook) {
      throw new ExcelExportException("the workbook has specified the type. You can not modify the workbook type again");
    }
    if (Objects.equals(type, ExcelFileType.XLS)) {
      workbook = new HSSFWorkbook();
    } else {
      workbook = new XSSFWorkbook();
    }
    return this;
  }

  @SuppressWarnings("unchecked")
  public ExcelExportBuilder build(String sheetName) {
    buildCheck();
    //创建表,如果已存在excelBook,那么新增表
    Sheet sheet = sheetName == null ? workbook.createSheet() : workbook.createSheet(sheetName);
    //写表头
    int rowNum = 0;
    Row headerRow = sheet.createRow(rowNum++);
    int[] tempCol = {0};
    this.headerAndConvert.forEach((k, v) -> {
      final Cell tempCell = headerRow.createCell(tempCol[0]++);
      tempCell.setCellValue(v.getDisplayHeader());
      sheet.setColumnWidth(tempCell.getColumnIndex(),
          v.getDisplayHeader().length() * CHINESE_CHARACTER_WIDTH + CHINESE_CHARACTER_WIDTH * 2);
    });

    //写表数据
    for (Map<String, Object> colData : this.mapData) {
      Row row = sheet.createRow(rowNum++);
      tempCol[0] = 0;
      this.headerAndConvert.forEach((k, v) -> {
        Cell cell = row.createCell(tempCol[0]++);
        Object value = v.getConvert().apply(colData.get(k));
        convertObjValue(cell, value);
      });
    }
    return this;
  }

  public void writeTo(String fileNameAndPath) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(new File(fileNameAndPath))) {
      workbook.write(fileOutputStream);
    } catch (IOException e) {
      throw new ExcelExportException("writeTo fail", e);
    } finally {
      try {
        workbook.close();
      } catch (IOException e) {
        // do nothing
      }
    }
  }

  @Override
  public void close() {
    try {
      if (null != workbook) {
        workbook.close();
      }
    } catch (IOException e) {
      throw new ExcelExportException("close workbook fail", e);
    }
  }


  private void buildCheck() throws ExcelExportException {
    if (null == mapData) {
      throw new ExcelExportException("this datasource can not be null");
    }
    // header没设置则自动使用map中的key作为表头
    if (null == headerAndConvert) {
      headerAndConvert = new LinkedHashMap<>();
      mapData.get(0).forEach((k,v) -> {
        headerAndConvert.put(k, ExcelHeader.create(k));
      });
    }

    if (mapData.size() > BIG_TABLE && workbook instanceof XSSFWorkbook) {
      this.workbook = new SXSSFWorkbook((XSSFWorkbook) workbook, 100);
    }
  }

  private void convertObjValue(Cell cell, Object value) {
    if (value instanceof Date) {
      cell.setCellValue((Date) value);
    } else {
      cell.setCellValue(String.valueOf(value));
    }
  }

}
