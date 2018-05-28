## 概述
项目主要应对日常开发环境中的一些小需求,比如说代码生成,数据库数据导出excel,数据库操作小脚本编写等等,目前才开始随着自己的需求改变而改变的项目.

## 使用建议
建议在工作项目中单独建立一个dev模块,该dev模块与项目本身尽量不要有依赖,只是作为一个子项目存在.
在该dev项目中引入该easy-dev-utils中相关工具类项目,然后实现自己的需求吧.


## base-sql
主要提供dbutils以及spring-jdbc快速操作数据库的模板.

### dbutils模板

```java
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
    // init拿到连接后即可操作数据库
    final List<Map<String, Object>> result = queryRunner.query(conn, "select * from user", new MapListHandler());
    Assert.assertEquals(result.size(),2);
  }
}
```

### springjdbc模板

```java

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
```

### 事务控制
`AbstractDbutilTemblate`是直接通过`Connection`操作数据库,因此其事务由`Connection`来控制,即`conn.setAutoCommit()`来控制.

`AbstractSpringJdbcTemplate`中提供了`TransactionTemplate`变量,事务直接通过`transactionTemplate.execute()`来自定义控制



## base-excel
自定义数据源导出对应excel表格的需求.目前不支持读取excel.

### 导出excel

1.提供数据源
```java
List<Map<String, Object>> result = ..... 可以使用base-sql从数据库直接查出Map

或

List<T> result = ..... 此方式内部会遍历T的字段,然后字段名作为key,值作为value生成上述Map结构. 
```

2.定义表头以及字段映射 
```java
LinkedHashMap<String, ExcelHeader> header = new LinkedHashMap<>();
header.put("id", ExcelHeader.create("用户id"));
header.put("avatar", ExcelHeader.create("用户头像"));
//自定义转换
header.put("role", ExcelHeader.create("用户角色", 
      (Function<String, String>) str -> "1".equals(str) ? "管理员" : "普通用户"));
//内置转换工具
header.put("gmt_create", ExcelHeader.create("用户创建时间",
      FuncitionConvertUtil.date2String));
```

或使用注解方式
```java

  @Data
  public static class User {

    @ExcelFiled(columnName = "用户id")
    private Long id;

    @ExcelFiled(columnName = "用户名")
    private String username;

    @ExcelFiled(columnName = "用户邮箱")
    private String email;

    @ExcelFiled(columnName = "用户头像")
    private String avatar;

    @ExcelFiled(columnName = "用户状态")
    private Integer status;

    @ExcelFiled(columnName = "用户上次登录时间",convert = DateToStringConvert.class)
    private Date last_login_date;

    @ExcelFiled(columnName = "用户创建时间",convert = DateToStringConvert.class)
    private Date gmt_create;
  }
```
表头为
```java
LinkedHashMap<String, ExcelHeader> header = ExcelHeader.builder().withHeader(User.class).build();
```


3.导出
```java

//单表
 ExcelExportUtil.fromMap(result)
     .displayHeader(header)
     .excelType(ExcelFileType.XLS)
     .build("用户表")
     .writeTo("/tmp/test1.xls");
     
//多表复合
ExcelExportUtil.fromMap(result)
    .displayHeader(header)
    .excelType(ExcelFileType.XLSX)
    .build("用户表1")
    .andFormMap(result) // 加入新表
    .displayHeader(header)
    .build("用户表2")
    .writeTo("/tmp/test2.xlsx");

```
4.注意事项

* 表头没设置的话会用map中的key作为表头

