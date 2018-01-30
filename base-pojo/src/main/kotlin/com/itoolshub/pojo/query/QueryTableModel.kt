package com.itoolshub.pojo.query

import com.itoolshub.pojo.model.table.TableModel
import java.sql.Connection

/**
 *  从数据库查询到具体的表信息
 * @author Quding Ding
 * @since 2018/1/29
 */
interface QueryTableModel {

    /**
     * 根据数据库以及表名称查询到具体的表信息
     * @param conn 数据库连接
     * @param database 数据库名称
     * @param table 表名称
     * @return 具体表
     */
    fun queryDetailOfTable(conn: Connection, database: String, table: String): TableModel?

}