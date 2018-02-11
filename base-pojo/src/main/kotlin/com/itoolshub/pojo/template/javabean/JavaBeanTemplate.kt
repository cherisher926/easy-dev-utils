package com.itoolshub.pojo.template.javabean

import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.model.table.toJavaTemplateModel
import com.itoolshub.pojo.model.template.All
import com.itoolshub.pojo.model.template.JavaTemplateModel
import com.itoolshub.pojo.template.AbstractTemplate
import com.itoolshub.pojo.template.mybatis.MybatisMapperIntefaceTemplate
import com.itoolshub.pojo.template.mybatis.MybatisMapperTemplate
import com.itoolshub.pojo.util.FiledUtils

/**
 * JavaBean模板
 * @author Quding Ding
 * @since 2018/1/29
 */
open class JavaBeanTemplate(private val tableModel: TableModel,
                            private val targetPath: String,
                            private var className: String? = null,
                            var javaModel: JavaTemplateModel? = null) : AbstractTemplate() {
    /**
     * 初始化逻辑
     */
    init {
        // 推测出className
        this.className = this.className ?: FiledUtils.parseTableNameToClassName(tableModel.tableName)
        // 获取数据
        javaModel = this.javaModel ?: tableModel.toJavaTemplateModel(this.packageName(), DEFAULT_DATA_TYPE_JAVA_TYPE)
        //写入数据源
        this.mapRootData.set(All.JAVABEAN_MODEL.key, javaModel!!)
    }

    override fun filePath(): String {
        return targetPath
    }

    override fun templateName(): String {
        return "javabean/JavaBeanTemplate.ftl"
    }

    override fun className(): String {
        return this.className!!
    }

    override fun suffix(): String {
        return "java"
    }

    /**
     * 根据文件路径推测出包名
     */
    private fun packageName(): String {
        return FiledUtils.parsePathToPackage(this.targetPath)
    }

    /**
     * 渲染Mybatis相关模板
     * @param targetPath 输出地址,为null则使用model的地址
     */
    fun renderTemplateMybatis(targetPath: String? = null) {
        val path = targetPath ?: this.targetPath
        // mybatis mapper接口
        val mybatisMapperIntefaceTemplate = MybatisMapperIntefaceTemplate(javaModel!!, path)
        mybatisMapperIntefaceTemplate.renderTemplate()
        // mybatis mapper文件
        val mybatisMapperTemplate = MybatisMapperTemplate(javaModel!!, path)
        mybatisMapperTemplate.renderTemplate()
    }

}