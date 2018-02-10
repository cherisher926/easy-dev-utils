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
    JAVABEAN_PACKAGE_NAME("JAVABEAN_PACKAGE_NAME",String::class.java),
    JAVABEAN_CLASS_NAME("JAVABEAN_CLASS_NAME",String::class.java),
    JAVABEAN_FILEDS("JAVABEAN_FILEDS",JavaTemplaetField::class.java), //集合

    // Mybatis Mapper Interface
    MYBATIS_INTEFACE_PACKAGE_NAME("MYBATIS_INTEFACE_PACKAGE_NAME",String::class.java),
    MYBATIS_INTEFACE_JAVA_MODEL("MYBATIS_INTEFACE_JAVA_MODEL",JavaTemplateModel::class.java),
    MYBATIS_ALL_STR_COLUMN("MYBATIS_ALL_STR_COLUMN",String::class.java)

}