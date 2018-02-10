package com.itoolshub.pojo.util

/**
 * 相关工具类
 */
object FiledUtils {

  private val PACKAGE_REPLACE = Regex("^.*src\\.main\\.java\\.?")

  /**
   * 转换一个path到package
   * @param path 输入文件夹路径
   * @return 具体的包名
   */
  fun parsePathToPackage(path: String): String {
    val target = path.replace("/", ".")
        .replace(PACKAGE_REPLACE, "")
    return if (target[0] == '.') target.substring(1) else target
  }

  /**
   * 转换表名(下划线)到class驼峰形式名称
   */
  fun parseTableNameToClassName(tableName: String): String {
    return tableName.splitToSequence('_')
        .map { s -> s[0].toUpperCase() + s.substring(1) }
        .joinToString("")
  }


  /**
   * 转换表字段名(下划线)到filed驼峰形式名称
   */
  fun parseColumnNameToFiledName(tableName: String): String {
    return tableName.splitToSequence('_')
        .mapIndexed { index, s -> if (index == 0) s else (s[0].toUpperCase() + s.substring(1)) }
        .joinToString("")
  }

  /**
   * 转换数据类型到Java类型
   */
  fun parseDataTypeToJavaType(dataType: String, dataTypeconversion: Map<String, String>): String {
    val realDataType = if (dataType.lastIndexOf(')') > 0) {
      dataType.substring(0, dataType.indexOf('('))
    } else {
      dataType
    }
    return dataTypeconversion.getOrDefault(realDataType, "Object")
  }

  fun parseDataType(dataType: String): String {
    return if (dataType.lastIndexOf(')') > 0) {
      dataType.substring(0, dataType.indexOf('('))
    } else {
      dataType
    }
  }


}