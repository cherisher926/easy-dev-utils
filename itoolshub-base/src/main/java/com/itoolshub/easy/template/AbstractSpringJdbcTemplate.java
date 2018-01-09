package com.itoolshub.easy.template;

import com.itoolshub.easy.util.PropertiesUtil;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author Quding Ding
 * @since 2018/1/9
 */
public abstract class AbstractSpringJdbcTemplate {

  private DataSource dataSource;

  protected JdbcTemplate jdbcTemplate;

  protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /**
   * 初始化函数
   */
  public void init(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }
  /**
   * 初始化函数
   */
  public void init(String url, String username, String password) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    init(dataSource);
  }

  /**
   * 初始化函数
   */
  public void init(String dataSourceProp) {
    Properties properties = PropertiesUtil.readClasspath(dataSourceProp);
    this.init(properties.getProperty("url"),
        properties.getProperty("username"),
        properties.getProperty("password"));
  }

  /**
   * 得到插入函数
   */
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return new SimpleJdbcInsert(dataSource);
  }

  /**
   * 得到连接
   */
  public Connection getConn() {
    try {
      if (null != this.dataSource) {
        return this.dataSource.getConnection();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

}
