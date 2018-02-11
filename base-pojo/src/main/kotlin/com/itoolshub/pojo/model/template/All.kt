package com.itoolshub.pojo.model.template

import java.util.*

/**
 *
 * @author Quding Ding
 * @since 2018/1/29
 */
enum class All(val key: String,val type: Class<*>) {
    /**
     * 当前时间,date类型
     */
    ALL_CURRENT_TIME("ALL_CURRENT_TIME", Date::class.java),

    // JavaBean相关模板字段
    JAVABEAN_MODEL("JAVABEAN_MODEL",JavaTemplateModel::class.java),

    // Mybatis Mapper Interface
    MYBATIS_INTEFACE_MODEL("MYBATIS_INTEFACE_MODEL",MybatisInterfaceTemplateModel::class.java),
    MYBATIS_XML_MODEL("MYBATIS_XML_MODEL",MybatisTemplateModel::class.java)

}