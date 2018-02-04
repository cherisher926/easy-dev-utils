package com.itoolshub.pojo.util

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import java.io.OutputStreamWriter


object FreeMarkerUtil {
    private val cfg = Configuration(Configuration.VERSION_2_3_22)

    init {
        cfg.setClassForTemplateLoading(FreeMarkerUtil::class.java, "/templates")
        cfg.defaultEncoding = "UTF-8"
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
    }

    /**
     * 渲染模板
     * @param rootData 数据
     * @param ftlName 模板名称
     * @param fileOut 输出对象
     */
    fun parseTemplate(rootData: MutableMap<String,Any>,
                      ftlName: String, fileOut: OutputStreamWriter) {
        val template: Template = cfg.getTemplate(ftlName)!!
        template.process(rootData, fileOut)
        fileOut.close()
    }

}