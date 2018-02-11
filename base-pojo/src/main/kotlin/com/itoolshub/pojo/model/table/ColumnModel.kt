package com.itoolshub.pojo.model.table

/**
 *  数据库列封装
 * @author Quding Ding
 * @since 2018/1/28
 */
data class ColumnModel(val filed: String,
                       val type: String,
                       val collation: String? = null,
                       val nullAble: Boolean = false,
                       val key: String? = null,
                       val default: String? = null,
                       val extra: String? = null,
                       val comment: String? = null) {

}