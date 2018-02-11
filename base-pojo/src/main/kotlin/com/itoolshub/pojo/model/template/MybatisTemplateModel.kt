package com.itoolshub.pojo.model.template

/**
 *
 * @author Quding Ding
 * @since 2018/2/10
 */
data class MybatisTemplateModel(val javaModel: JavaTemplateModel,
                                val className: String,
                                val namespace: String) {

  val allColumns: String
    get() = javaModel.fileds.asSequence().map { it.originName }.joinToString()

}