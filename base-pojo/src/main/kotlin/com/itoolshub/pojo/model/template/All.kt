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
    ALL_CURRENT_TIME("currentTime", Date::class.java),

    JAVABEAN_PACKAGE_NAME("packageName",String::class.java),
    JAVABEAN_CLASS_NAME("className",String::class.java),
    JAVABEAN_FILED("fileds",JavaTemplaetField::class.java), //集合

}