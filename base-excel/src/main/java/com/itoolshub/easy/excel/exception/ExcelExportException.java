package com.itoolshub.easy.excel.exception;

/**
 * excel export related exceptions
 * @author Quding Ding
 * @since 2018/2/6
 */
public class ExcelExportException extends RuntimeException {

  public ExcelExportException(String message) {
    super(message);
  }

  public ExcelExportException(String message, Throwable cause) {
    super(message, cause);
  }
}
