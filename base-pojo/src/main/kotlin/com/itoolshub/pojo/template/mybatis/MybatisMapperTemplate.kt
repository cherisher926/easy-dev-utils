package com.itoolshub.pojo.template.mybatis

import com.itoolshub.pojo.model.template.All
import com.itoolshub.pojo.model.template.JavaTemplateModel

/**
 *
 * @author Quding Ding
 * @since 2018/2/9
 */
class MybatisMapperTemplate(private val javaModel: JavaTemplateModel,
                            private val targetPath: String): MybatisMapperIntefaceTemplate(javaModel, targetPath) {

  init {
    this.mapRootData.put(All.MYBATIS_ALL_STR_COLUMN.key,
        javaModel.fileds.asSequence().map { it.originName }.joinToString())
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