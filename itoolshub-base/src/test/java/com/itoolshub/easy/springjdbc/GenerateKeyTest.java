package com.itoolshub.easy.springjdbc;

import com.google.common.collect.Maps;
import com.itoolshub.easy.template.AbstractSpringJdbcTemplate;

import org.h2.tools.RunScript;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Quding Ding
 * @since 2018/1/9
 */
public class GenerateKeyTest extends AbstractSpringJdbcTemplate {


  @Before
  public void before() throws SQLException {
    // init中初始化连接
    init("db1.properties");
    RunScript.execute(getConn(),
        new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data.sql")));
  }


  @Test
  public void testGenerateKey() {
    String sql = "INSERT INTO `user` (`username`, `email`, `avatar`, `last_login_date`, `status`, `role`, `gmt_create`, `gmt_update`)\n" +
        "VALUES\n" +
        "\t( ?, ?, ?,'2017-09-16 09:47:01',1,'1','2017-09-09 11:02:02','2017-09-09 11:02:02')";


    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
      ps.setString(1, "张三");
      ps.setString(2, "qq@qq.com");
      ps.setString(3, "http://iiiiiiii.png");
      return ps;
    }, keyHolder);
    Assert.assertEquals(keyHolder.getKey(),3);
  }

  @Test
  public void testGenerateKey2() {
    final SimpleJdbcInsert jdbcInsert = getSimpleJdbcInsert();
    jdbcInsert.withTableName("user")
        .usingColumns("username","email","avatar")
        .usingGeneratedKeyColumns("id");
    Map<String, Object> params = Maps.newHashMap();
    params.put("username", "张三");
    params.put("email", "11@qq.com");
    params.put("avatar", "http://123.png");
    final Number id = jdbcInsert.executeAndReturnKey(params);
    Assert.assertEquals(id,3);
  }

}
