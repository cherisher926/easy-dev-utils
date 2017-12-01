
# easy-excel

日常工作中偶尔有导出一些临时数据的需求,因此写了个工具帮助自己尽快完成需求.

### 使用方法
```java
    //查询出结果,使用Map存储,当然dbUtils也支持bean存储
    final List<Map<String, Object>> result = queryRunner.query(conn, "select * from user", new MapListHandler());

    //表格对应的表头,以及该表头的数据处理
    LinkedHashMap<String, ExcelHeader> header = new LinkedHashMap<>();
    header.put("id", ExcelHeader.create("用户id"));
    header.put("username", ExcelHeader.create("用户名"));
    header.put("email", ExcelHeader.create("用户邮箱"));
    header.put("avatar", ExcelHeader.create("用户头像"));
    //对于日期使用转换器,转换器为java8的Function函数实现
    header.put("last_login_date", ExcelHeader.create("用户上次登录时间", FuncitionConvertUtil.date2String));
    header.put("status", ExcelHeader.create("用户id"));
    header.put("role", ExcelHeader.create("用户id"));
    //对于日期使用转换器,转换器为java8的Function函数实现
    header.put("gmt_create", ExcelHeader.create("用户id",FuncitionConvertUtil.date2String));

    //导出表
    /**
     * 查出来的map直接导出表格
     */
    ExcelExportUtil.fromMap(result)
        .displayHeader(header)
        .excelType(ExcelExportUtil.ExcelFileType.XLS)
        .build("用户表")
        .writeTo("/tmp/test1.xls");
    /**
     * 导出多张表
     */
    ExcelExportUtil.fromMap(result)
        .displayHeader(header)
        .excelType(ExcelExportUtil.ExcelFileType.XLSX)
        .build("用户表1")
        .andFormMap(result)
        .displayHeader(header)
        .build("用户表2")
        .writeTo("/tmp/test2.xlsx");
```

导出表格展示

test1.xls

![](http://oobu4m7ko.bkt.clouddn.com/1512115263.png?imageMogr2/thumbnail/!70p)

test2.xlsx

![](http://oobu4m7ko.bkt.clouddn.com/1512115321.png?imageMogr2/thumbnail/!70p)

