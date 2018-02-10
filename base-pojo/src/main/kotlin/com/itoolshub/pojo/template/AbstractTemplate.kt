package com.itoolshub.pojo.template

import com.itoolshub.pojo.model.template.All
import com.itoolshub.pojo.util.FreeMarkerUtil
import java.io.File
import java.io.FileWriter
import java.util.*

/**
 *
 * @author Quding Ding
 * @since 2018/2/9
 */
abstract class AbstractTemplate : Template {

  /**
   * 存储数据的容器
   */
  protected val mapRootData: MutableMap<String, Any> = mutableMapOf()

  /**
   * 映射表
   */
  protected val DEFAULT_DATA_TYPE_JAVA_TYPE: Map<String, String> = mapOf(
      Pair("tinyint", "Integer"),
      Pair("int", "Integer"),
      Pair("varchar", "String"),
      Pair("text", "String"),
      Pair("char", "String"),
      Pair("bigint", "Long"),
      Pair("datetime", "Date"),
      Pair("timestamp", "Date"),
      Pair("float", "Double")
  )

  /**
   * 公共字段在这里set
   */
  init {
    this.mapRootData.set(All.ALL_CURRENT_TIME.key, Date())
  }

  override fun fileName(): String {
    return className() + dot + suffix()
  }

  /**
   * 输出文件
    */
  private fun writerTargetOutFile(): FileWriter {
    val fullFilePath = System.getProperty("user.home") + File.separatorChar +
        filePath()
    val file = File(fullFilePath)
    if (!file.exists() && file.mkdirs()) {
      println("init fullFilePath $fullFilePath")
    }
    return FileWriter(fullFilePath + File.separatorChar + fileName())
  }


  override fun renderTemplate() {
    FreeMarkerUtil.parseTemplate(this.mapRootData(), this.templateName(), this.writerTargetOutFile())
  }

  override fun addMapRootData(key: String, value: Any): MutableMap<String, Any> {
    mapRootData.set(key, value)
    return mapRootData
  }

  override fun mapRootData(): MutableMap<String, Any> {
    return mapRootData
  }
}