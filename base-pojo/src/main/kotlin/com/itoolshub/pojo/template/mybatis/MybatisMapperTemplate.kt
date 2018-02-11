package com.itoolshub.pojo.template.mybatis

import com.itoolshub.pojo.model.template.All
import com.itoolshub.pojo.model.template.JavaTemplateModel
import com.itoolshub.pojo.model.template.MybatisTemplateModel
import com.itoolshub.pojo.util.FiledUtils

/**
 *
 * @author Quding Ding
 * @since 2018/2/9
 */
class MybatisMapperTemplate(private val javaModel: JavaTemplateModel,
                            private val targetPath: String): MybatisMapperIntefaceTemplate(javaModel, targetPath) {

  init {
    val tempModel = MybatisTemplateModel(javaModel,className(),
        FiledUtils.parsePathToPackage(targetPath)+ super.className())
      this.mapRootData.put(All.MYBATIS_XML_MODEL.key, tempModel)
  }

  override fun templateName(): String {
    return "mybatis/MybatisMapperTemplate.ftl"
  }

  override fun className(): String {
    return javaModel.className + "Mapper"
  }

  override fun suffix(): String {
    return "xml"
  }

  override fun filePath(): String {
    return targetPath
  }
}