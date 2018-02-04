package com.itoolshub.pojo.model.template

import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.util.FiledUtils

/**
 * Java模板对应的字段类
 * @author Quding Ding
 * @since 2018/1/30
 */
data class JavaTemplateModel (var className: String,val packageName: String,
                              private val tableModel: TableModel) {

  var fileds: MutableList<JavaTemplaetField> = mutableListOf()

  init {
    // 转换成Java所需要的驼峰类
    fileds = tableModel.columns.asSequence()
        .map {
          JavaTemplaetField(
              FiledUtils.parseColumnNameToFiledName(it.filed),
              FiledUtils.parseDataTypeToJavaType(it.type),
              it.comment,
              it.default ?: "null"
          )
        }.toMutableList()
  }
}

class JavaTemplaetField (val name: String,val type: String,
                         val comment: String? = "",
                         val defaultVal: String)