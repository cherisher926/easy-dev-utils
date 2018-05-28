package cn.ifreehub.easy.excel.util;

import lombok.Data;

import org.junit.Assert;
import org.junit.Test;

import cn.ifreehub.easy.excel.annotation.ExcelFiled;
import cn.ifreehub.easy.excel.convert.DefaultConvert;
import cn.ifreehub.easy.excel.convert.UpperStringConvert;
import cn.ifreehub.easy.excel.helper.BeanHelper;
import cn.ifreehub.easy.excel.model.ExcelHeader;

import java.util.LinkedHashMap;
import java.util.Map;

public class BeanHelperTest {

  @Test
  public void testToMap() {
    User user = new User();
    user.setName("张三");
    user.setPwd("123456");
    Map<String, Object> mapResult = BeanHelper.toMap(user);
    Assert.assertTrue("张三".equals(mapResult.get("name")));
    Assert.assertTrue("123456".equals(mapResult.get("pwd")));
  }


  @Test
  public void testToHeaderMap() {
    LinkedHashMap<String, ExcelHeader> headerMap = BeanHelper.toHeaderMap(User.class);
    Assert.assertEquals("名称", headerMap.get("name").getDisplayHeader());
    Assert.assertTrue(headerMap.get("name").getConvert() instanceof UpperStringConvert);
    Assert.assertEquals("密码", headerMap.get("pwd").getDisplayHeader());
    Assert.assertTrue(headerMap.get("pwd").getConvert() instanceof DefaultConvert);
  }


  @Data
  class User {
    @ExcelFiled(columnName = "名称",convert = UpperStringConvert.class)
    private String name;

    @ExcelFiled(columnName = "密码")
    private String pwd;
  }
}