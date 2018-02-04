package com.itoolshub.pojo.query

import com.itoolshub.easy.template.AbstractDbutilTemblate
import com.itoolshub.pojo.model.table.ColumnModel
import com.itoolshub.pojo.model.table.TableModel
import org.apache.commons.dbutils.handlers.MapListHandler
import org.springframework.util.CollectionUtils
import java.sql.Connection
import java.util.stream.Collectors

object MysqlColumnQuery : AbstractDbutilTemblate(),QueryTableModel {
    /**
     * 结果转换器
     */
    private val mapListHandler = MapListHandler()


    override fun queryDetailOfTable(conn: Connection,database: String, table: String): TableModel? {
        val tableDetail: List<Map<String,Any>> = this.queryRunner.query(conn, "SHOW FULL COLUMNS" +
                " FROM $database.$table", mapListHandler)
        if (CollectionUtils.isEmpty(tableDetail)) {
            return null
        }
        // 转换为自定义Model
        val columns = tableDetail.stream().map {
            columnMap ->
            val columnModel = ColumnModel(
                    columnMap.get("Field") as String,
                    columnMap.get("Type") as String,
                    columnMap.get("Null") as String == "YES"
            )
            columnModel.key = columnMap.get("Key") as? String
            columnModel.collation = columnMap.get("Collation") as? String
            columnModel.default = columnMap.get("Default") as? String
            columnModel.comment = columnMap.get("Comment") as? String
            columnModel.extra = columnMap.get("Extra") as? String
            return@map columnModel
        }.collect(Collectors.toList())

        return TableModel(table, columns)
    }

}