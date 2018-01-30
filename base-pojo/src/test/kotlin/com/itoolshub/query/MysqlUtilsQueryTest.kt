package com.itoolshub.query

import com.itoolshub.pojo.query.MysqlUtilsQuery
import org.h2.tools.RunScript
import org.junit.Ignore
import org.junit.Test
import java.io.InputStreamReader
import java.sql.Connection

/**
 *  h2不支持此语法
 * @author Quding Ding
 * @since 2018/1/28
 */
@Ignore
class MysqlUtilsQueryTest {

    var conn: Connection = MysqlUtilsQuery.init("db1.properties")

    init {
        // init中初始化连接
        RunScript.execute(conn,
                InputStreamReader(this.javaClass.classLoader.getResourceAsStream("data.sql")))
    }

    @Test
    fun UsersTable() {
        val tableModel = MysqlUtilsQuery.queryDetailOfTable(conn, "rss", "user")

    }



}