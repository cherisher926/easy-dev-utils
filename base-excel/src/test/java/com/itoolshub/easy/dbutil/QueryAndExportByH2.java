package com.itoolshub.easy.dbutil;

import cn.ifreehub.easy.excel.convert.FuncitionConvertUtil;
import cn.ifreehub.easy.excel.model.ExcelHeader;
import cn.ifreehub.easy.excel.core.ExcelExportUtil;
import cn.ifreehub.easy.excel.model.ExcelFileType;
import cn.ifreehub.easy.template.AbstractDbutilTemblate;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Quding Ding
 * @since 2017/11/30
 */
public class QueryAndExportByH2 extends AbstractDbutilTemblate{

  private Connection conn;

  @Before
  public void before() throws SQLException {
    // init中初始化连接
    conn = init("db1.properties");
    RunScript.execute(conn,
        new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data.sql")));
  }

  @Test
  public void testQueryAndExport() throws SQLException {
    //查询出结果,使用Map存储,当然dbUtils也支持bean存储
    final List<Map<String, Object>> result = queryRunner.query(conn, "select * from user", new MapListHandler());

    //表格对应的表头,以及该表头的数据处理
    LinkedHashMap<String, ExcelHeader> header = new LinkedHashMap<>();
    header.put("id", ExcelHeader.create("用户id"));
    header.put("username", ExcelHeader.create("用户名"));
    header.put("email", ExcelHeader.create("用户邮箱"));
    header.put("avatar", ExcelHeader.create("用户头像"));
    header.put("status", ExcelHeader.create("用户状态"));
    //自定义转换
    header.put("role", ExcelHeader.create("用户角色", (Function<String, String>) str -> "1".equals(str) ? "管理员" : "普通用户"));
    //对于日期使用转换器,转换器为java8的Function函数实现
    header.put("last_login_date", ExcelHeader.create("用户上次登录时间", FuncitionConvertUtil.date2String));
    header.put("gmt_create", ExcelHeader.create("用户创建时间",FuncitionConvertUtil.date2String));

    //导出表
    /**
     * 查出来的map直接导出表格
     */
    ExcelExportUtil.fromMap(result)
        .displayHeader(header)
        .excelType(ExcelFileType.XLS)
        .build("用户表")
        .writeTo("/tmp/test1.xls");
    /**
     * 导出多张表
     */
    ExcelExportUtil.fromMap(result)
        .displayHeader(header)
        .excelType(ExcelFileType.XLSX)
        .build("用户表1")
        .andFormMap(result)
        .displayHeader(header)
        .build("用户表2")
        .writeTo("/tmp/test2.xlsx");
  }

}
