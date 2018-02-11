package com.itoolshub.pojo.template.javabean

import com.google.common.collect.Lists
import com.itoolshub.pojo.model.table.ColumnModel
import com.itoolshub.pojo.model.table.TableModel
import org.junit.Test

class JavaBeanTemplateTest {

  @Test
  fun render() {
    val tableModel = mockUserTable()
    // 相对于~目录下的地址
    val targetPath = "workspace/quding-git/easy-dev-utils/base-example/src/main/java/com/itoolshub/easy/example/test"
    // java导出模板
    val javaBeanTemplate = JavaBeanTemplate(tableModel, targetPath)
    //导出javabean
    javaBeanTemplate.renderTemplate()
    //导出mybatis相关文件
    javaBeanTemplate.renderTemplateMybatis()
  }


  fun mockUserTable(): TableModel {
    val id = ColumnModel("id", "int(11) unsigned", null, false, "PRI", null, "auto_increment", "主键")
    val username = ColumnModel("username", "varchar(30)", "utf8mb4_general_ci", false, null, null, null, "用户名")
    val email = ColumnModel("email", "varchar(128)", "utf8mb4_general_ci", false, null, null, null, "邮箱")
    val lastLoginDate = ColumnModel("last_login_date", "timestamp", null, false, null, "CURRENT_TIMESTAMP", null, "上次登录时间")
    val status = ColumnModel("status", "tinyint(4)", null, false, null, "1", null, "状态")
    return TableModel("user", Lists.newArrayList(id, username, email, lastLoginDate, status))
  }
}