package com.itoolshub.pojo.template.javabean

import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.model.template.All
import com.itoolshub.pojo.model.template.JavaTemplateModel
import com.itoolshub.pojo.template.Template
import com.itoolshub.pojo.util.parsePathToPackage
import java.io.File
import java.io.FileWriter
import java.util.*

/**
 * JavaBean模板
 * @author Quding Ding
 * @since 2018/1/29
 */
open class JavaBeanTemplate(private val className: String,
                            private val targetPath: String,
                            private val tableModel: TableModel) : Template {
    /**
     * 存储数据的容器
     */
    private val mapRootData: MutableMap<String, Any> = mutableMapOf()

    init {
        val javaTemplateModel = JavaTemplateModel(this.className,this.packageName(),tableModel)
        this.mapRootData.set(All.ALL_CURRENT_TIME.key, Date())
        this.mapRootData.set(All.JAVABEAN_PACKAGE_NAME.key, javaTemplateModel.packageName)
        this.mapRootData.set(All.JAVABEAN_CLASS_NAME.key, javaTemplateModel.className)
        this.mapRootData.set(All.JAVABEAN_FILED.key, javaTemplateModel.fileds)
    }

    override fun templateName(): String {
        return "javabean/JavaBeanTemplate.ftl"
    }

    override fun targetOutFile(): FileWriter {
        val filePath = System.getProperty("user.home") + File.separatorChar + this.targetPath +
                File.separatorChar + fullFileName()
        return FileWriter(File(filePath))
    }

    private fun packageName(): String {
        return parsePathToPackage(this.targetPath)
    }

    override fun mapRootData(): MutableMap<String, Any> {
        return this.mapRootData
    }

    override fun className(): String {
        return this.className
    }

    override fun suffix(): String {
        return "java"
    }

}