package com.itoolshub.pojo.model.template

/**
 *
 * @author Quding Ding
 * @since 2018/2/10
 */
data class MybatisInterfaceTemplateModel(val javaModel: JavaTemplateModel,
                                         val packageName: String) {
  /**
   * Mapper类名
   */
  val className: String
    get() = javaModel.className + "Mapper"

  /**
   * class全路径名称
   */
  val fullClassName: String
    get() = packageName + '.' + className

}