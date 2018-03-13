package com.itoolshub.easy.excel.exception;

/**
 * excel export related exceptions
 * @author Quding Ding
 * @since 2018/2/6
 */
public class ExcelReadException extends RuntimeException {

  public ExcelReadException(String message) {
    super(message);
  }

  public ExcelReadException(Throwable cause) {
    super(cause);
  }

  public ExcelReadException(String message, Throwable cause) {
    super(message, cause);
  }
}
