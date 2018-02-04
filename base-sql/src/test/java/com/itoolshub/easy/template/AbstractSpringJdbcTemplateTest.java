package com.itoolshub.easy.template;

import org.h2.tools.RunScript;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AbstractSpringJdbcTemplateTest extends AbstractSpringJdbcTemplate{
  @Before
  public void before() throws SQLException {
    // init中初始化连接
    init("db1.properties");
    RunScript.execute(getConn(),
        new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data.sql")));
  }

  @Test
  public void testQueryListMap() {
    final List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from user");
    Assert.assertEquals(result.size(),2);
  }

}