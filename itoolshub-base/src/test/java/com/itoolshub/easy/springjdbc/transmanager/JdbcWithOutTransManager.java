package com.itoolshub.easy.springjdbc.transmanager;

import com.itoolshub.easy.template.AbstractSpringJdbcTemplate;

import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * @author Quding Ding
 * @since 2018/1/10
 */
public class JdbcWithOutTransManager extends AbstractSpringJdbcTemplate {


  @Before
  public void before() throws SQLException {
    // init中初始化连接
    init("db1.properties");
    RunScript.execute(getConn(),
        new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data.sql")));
  }

  /**
   * 默认是自动commit的
   */
  @Test
  public void testWithoutTrans() throws SQLException {
    System.out.println(jdbcTemplate.getDataSource().getConnection().getAutoCommit());
  }


}
