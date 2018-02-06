package com.itoolshub.easy.excel.util;

import lombok.Data;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class MapUtilsTest {

  @Test
  public void toMap() {
    User user = new User();
    user.setName("张三");
    user.setPwd("123456");
    Map<String, Object> mapResult = MapUtils.toMap(user);
    Assert.assertTrue("张三".equals(mapResult.get("name")));
    Assert.assertTrue("123456".equals(mapResult.get("pwd")));
  }

  @Data
  class User {
    private String name;

    private String pwd;
  }
}