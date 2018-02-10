package com.itoolshub.pojo.model.table

import com.itoolshub.pojo.model.template.JavaTemplateModel
import com.itoolshub.pojo.util.FiledUtils

/**
 *
 * @author Quding Ding
 * @since 2018/1/28
 */
data class TableModel(val tableName: String,
                      val columns: List<ColumnModel>) {
}

fun TableModel.toJavaTemplateModel(packageName: String,dataTypeconversion: Map<String, String>): JavaTemplateModel {
  val className = FiledUtils.parseTableNameToClassName(this.tableName)
  return JavaTemplateModel(className, packageName, this, dataTypeconversion)
}