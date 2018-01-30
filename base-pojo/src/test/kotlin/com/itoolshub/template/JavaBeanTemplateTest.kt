package com.itoolshub.template

import com.itoolshub.pojo.model.table.ColumnModel
import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.template.Template
import com.itoolshub.pojo.template.javabean.JavaBeanTemplate
import com.itoolshub.pojo.util.FreeMarkerUtil
import org.junit.Test

/**
 *
 * @author Quding Ding
 * @since 2018/1/29
 */
class JavaBeanTemplateTest {

    @Test
    fun testParse() {

        val element = ColumnModel("name", "tinyInt", false)
        element.comment = "用户名"
        val columns: List<ColumnModel> = listOf(element)

        val tableModel: TableModel = TableModel("user", columns)

        var template: Template = JavaBeanTemplate("Quding", "Downloads/quding", tableModel)
        FreeMarkerUtil.parseTemplate(template.mapRootData(), template.templateName(), template.targetOutFile())
    }

}