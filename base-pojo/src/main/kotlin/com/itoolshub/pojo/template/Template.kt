package com.itoolshub.pojo.template

/**
 *
 * @author Quding Ding
 * @since 2018/1/29
 */
interface Template {

  val dot: Char get() = '.'

  /**
   * 得到模板地址
   */
  fun templateName(): String

  /**
   * 文件名称
   */
  fun className(): String

  /**
   * 文件类型后缀名
   */
  fun suffix(): String

  /**
   * 文件所在路径
   */
  fun filePath(): String;

  /**
   * 得到文件名包括后缀
   */
  fun fileName(): String

  /**
   * 得到输入数据
   */
  fun mapRootData(): MutableMap<String, Any>

  /**
   * 往输入数据添加模板
   */
  fun addMapRootData(key: String, value: Any): MutableMap<String, Any>

  /**
   * 渲染模板
   */
  fun renderTemplate()

}