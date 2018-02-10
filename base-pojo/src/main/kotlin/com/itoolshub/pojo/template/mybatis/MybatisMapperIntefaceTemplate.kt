package com.itoolshub.pojo.template.mybatis

import com.itoolshub.pojo.model.template.All
import com.itoolshub.pojo.model.template.JavaTemplateModel
import com.itoolshub.pojo.template.AbstractTemplate
import com.itoolshub.pojo.util.FiledUtils

/**
 *
 * @author Quding Ding
 * @since 2018/2/9
 */
open class MybatisMapperIntefaceTemplate(private val javaModel: JavaTemplateModel,
                                         private val targetPath: String) : AbstractTemplate(){

  init {
    mapRootData.put(All.MYBATIS_INTEFACE_JAVA_MODEL.key, javaModel)
    mapRootData.put(All.MYBATIS_INTEFACE_PACKAGE_NAME.key, FiledUtils.parsePathToPackage(targetPath))
  }

  override fun filePath(): String {
    return targetPath
  }

  override fun templateName(): String {
    return "mybatis/MybatisMapperIntefaceTemplate.ftl"
  }

  override fun suffix(): String {
    return "java"
  }

  override fun className(): String {
    return javaModel.className + "Mapper"
  }

}