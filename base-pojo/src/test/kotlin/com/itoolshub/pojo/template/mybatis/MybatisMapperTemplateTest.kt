package com.itoolshub.pojo.template.mybatis

import com.itoolshub.pojo.model.table.ColumnModel
import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.template.javabean.JavaBeanTemplate
import org.junit.Test

class MybatisMapperTemplateTest {

  @Test
  fun testParse() {
    val filed1 = ColumnModel("name", "varchar", true)
    val filed2 = ColumnModel("pwd", "datetime", false)
    val filed3 = ColumnModel("age", "int(5)", false)
    filed3.default = "0"
    val tableModel = TableModel("user", listOf(filed1, filed2, filed3))

    // 定义文件,渲染
    val javaTemplate = JavaBeanTemplate(tableModel, "Downloads/quding")
    val mybatisTemplate = MybatisMapperTemplate(javaTemplate.javaModel!!,"Downloads/quding")
    mybatisTemplate.renderTemplate()
  }
}