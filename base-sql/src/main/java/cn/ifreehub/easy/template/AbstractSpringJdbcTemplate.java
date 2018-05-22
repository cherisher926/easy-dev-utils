package cn.ifreehub.easy.template;

import cn.ifreehub.util.PropertiesUtil;

import lombok.Getter;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author Quding Ding
 * @since 2018/1/9
 */
public abstract class AbstractSpringJdbcTemplate {

  @Getter
  private DataSource dataSource;

  protected JdbcTemplate jdbcTemplate;

  protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  /**
   * 用于事务控制模板
   */
  @Getter
  protected TransactionTemplate transactionTemplate;

  /**
   * 初始化函数
   */
  public void init(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    //初始化事务模板
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    this.transactionTemplate = new TransactionTemplate();
    this.transactionTemplate.setTransactionManager(dataSourceTransactionManager);
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
