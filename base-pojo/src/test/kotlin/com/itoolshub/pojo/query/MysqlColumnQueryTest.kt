package com.itoolshub.pojo.query

import org.junit.Ignore
import org.junit.Test
import java.sql.Connection

/**
 *  h2不支持此语法
 * @author Quding Ding
 * @since 2018/1/28
 */
@Ignore
class MysqlColumnQueryTest {

    var conn: Connection = MysqlColumnQuery.init("db1.properties")


    @Test
    fun UsersTable() {
        val tableModel = MysqlColumnQuery.queryDetailOfTable(conn, "rss", "user")
    }



}