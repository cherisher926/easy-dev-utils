package com.itoolshub.pojo.template

import java.io.FileWriter

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
     * 输出文件地址,请填写~/下的目录
     */
    fun targetOutFile(): FileWriter

    /**
     * 文件类型后缀名
     */
    fun suffix(): String

    /**
     * 文件名称
     */
    fun className(): String

    /**
     * 得到文件全名
     */
    fun fullFileName(): String {
        return className() + dot + suffix()
    }

    /**
     * 得到输入数据
     */
    fun mapRootData(): MutableMap<String, Any>

    /**
     * 往输入数据添加模板
     */
    fun addMapRootData(key: String, value: Any): MutableMap<String, Any> {
        mapRootData().set(key, value)
        return mapRootData()
    }
}