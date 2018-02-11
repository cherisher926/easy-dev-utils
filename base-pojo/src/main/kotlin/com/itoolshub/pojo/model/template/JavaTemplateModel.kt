package com.itoolshub.pojo.model.template

import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.util.FiledUtils

/**
 * Java模板对应的字段类
 * @author Quding Ding
 * @since 2018/1/30
 */
data class JavaTemplateModel(var className: String, val packageName: String,
                             private val tableModel: TableModel,
                             private val dataTypeconversion: Map<String, String>) {

  var fileds: MutableList<JavaTemplaetField> = mutableListOf()

  init {
    // 转换成Java所需要的驼峰类
    fileds = tableModel.columns.asSequence()
        .map {
          JavaTemplaetField(
              FiledUtils.parseColumnNameToFiledName(it.filed),
              it.filed,
              FiledUtils.parseDataTypeToJavaType(it.type, dataTypeconversion),
              FiledUtils.parseDataType(it.type),
              it.comment,
              it.default ?: "null"
          )
        }.toMutableList()
  }

  /**
   * 原始表名
   */
  val originTableName: String
    get() = tableModel.tableName

  /**
   * class全路径名称
   */
  val fullClassName: String
    get() = packageName + '.' + className

}

class JavaTemplaetField(val javaName: String,
                        val originName: String,
                        val javaType: String,
                        val originType: String,
                        val comment: String? = "",
                        val defaultVal: String)
