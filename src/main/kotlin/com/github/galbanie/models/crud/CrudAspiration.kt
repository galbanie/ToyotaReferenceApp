package com.github.galbanie.models.crud

import com.github.galbanie.models.Aspiration
import com.github.galbanie.models.db.Aspirations
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * Created by Galbanie on 2017-04-23.
 */

class CrudAspiration : CrudTable<Aspiration>{
    override fun createTable() = SchemaUtils.create(Aspirations)

    override fun create(m: Aspiration): Aspiration {
        m.id = Aspirations.insert(toRow(m)).get(Aspirations.id)
        return m
    }

    override fun findAll() = Aspirations.selectAll().map { fromRow(it) }

    override fun deleteAll() = Aspirations.deleteAll()

    override fun delete(m: Aspiration) = Aspirations.deleteWhere { Aspirations.id eq m.id }

    override fun findBy(columnName: String, value: Any): Iterable<Aspiration>? {
        if (columnName == "aspirationID") return Aspirations.select { Aspirations.id eq value as Int }.map { fromRow(it) }
        else if (columnName == "aspiration") return Aspirations.select { Aspirations.aspiration eq value as String }.map { fromRow(it) }
        return emptyList()
    }

    override fun update(m: Aspiration) {
        Aspirations.update({ Aspirations.id eq m.id}){
            it[Aspirations.aspiration] = m.aspiration
        }
    }

    override fun findColumnName(): Iterable<String> {
        var c = arrayListOf<String>()
        Aspirations.columns.forEach{
            c.add(it.name)
        }
        return c
    }

    private fun toRow(m: Aspiration): Aspirations.(UpdateBuilder<*>) -> Unit = {
        if (m.id != null) it[id] = m.id
        it[aspiration] = m.aspiration
    }

    private fun fromRow(r: ResultRow) = Aspiration(r[Aspirations.id], r[Aspirations.aspiration])

}