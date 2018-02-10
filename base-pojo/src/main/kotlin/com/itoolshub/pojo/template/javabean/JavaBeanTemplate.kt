package com.itoolshub.pojo.template.javabean

import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.model.table.toJavaTemplateModel
import com.itoolshub.pojo.model.template.All
import com.itoolshub.pojo.model.template.JavaTemplateModel
import com.itoolshub.pojo.template.AbstractTemplate
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
        this.mapRootData.set(All.JAVABEAN_PACKAGE_NAME.key, javaModel!!.packageName)
        this.mapRootData.set(All.JAVABEAN_CLASS_NAME.key, javaModel!!.className)
        this.mapRootData.set(All.JAVABEAN_FILEDS.key, javaModel!!.fileds)
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

}