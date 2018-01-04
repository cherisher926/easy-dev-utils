package com.itoolshub.easy.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class PropertiesUtilTest {

  @Test
  public void testReadClasspath() {
    final Properties properties = PropertiesUtil.readClasspath("db1.properties");
    Assert.assertEquals(properties.size(),4);
    Assert.assertEquals(properties.getProperty("driverClassName"),"com.mysql.jdbc.Driver");
  }

}