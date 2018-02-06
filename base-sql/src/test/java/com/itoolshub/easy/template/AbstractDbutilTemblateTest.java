package com.itoolshub.easy.template;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.h2.tools.RunScript;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AbstractDbutilTemblateTest extends AbstractDbutilTemblate {
  private Connection conn;

  @Before
  public void before() throws SQLException {
    // init中初始化连接
    conn = init("db1.properties");
    RunScript.execute(conn,
        new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data.sql")));
  }

  @Test
  public void testQueryListMap() throws SQLException {
    final List<Map<String, Object>> result = queryRunner.query(conn, "select * from user", new MapListHandler());
    Assert.assertEquals(result.size(),2);
  }

}