package com.itoolshub.pojo.model.table

/**
 *  数据库列封装
 * @author Quding Ding
 * @since 2018/1/28
 */
data class ColumnModel(val filed: String,val type: String,val nullAble: Boolean) {

    var collation: String? = null

    var key: String? = null

    var default: String? = null

    var extra: String? = null

    var comment: String? = null

}