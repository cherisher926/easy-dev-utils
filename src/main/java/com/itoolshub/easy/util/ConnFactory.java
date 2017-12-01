package com.itoolshub.easy.util;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据库连接工厂
 * @author Quding Ding
 * @since 2017/11/30
 */
public class ConnFactory {

  private Properties properties;

  public ConnFactory(Properties properties) {
    final boolean loader = DbUtils.loadDriver(properties.getProperty("driverClassName"));
    if (!loader) {
      throw new RuntimeException("load db driver fail");
    }
    this.properties = properties;
  }

  public Connection getConnect() {
    try {
      return DriverManager.getConnection(
          this.properties.getProperty("url"),
          this.properties.getProperty("username"),
          this.properties.getProperty("password"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
