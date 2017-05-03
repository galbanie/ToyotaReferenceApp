package com.github.galbanie.models.crud

import com.github.galbanie.models.Make
import com.github.galbanie.models.db.Makes
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import kotlin.reflect.KClass

/**
 * Created by Galbanie on 2017-04-21.
 */

class CrudMake : CrudTable<Make>{

    override fun createTable() = SchemaUtils.create(Makes)

    override fun create(m: Make): Make {
        m.id = Makes.insert(toRow(m)).get(Makes.id)
        return m
    }

    override fun findAll() = Makes.selectAll().map { fromRow(it) }

    override fun deleteAll() = Makes.deleteAll()

    override fun delete(m: Make) = Makes.deleteWhere { Makes.id eq m.id }

    override fun findBy(columnName: String, value: Any) : Iterable<Make>? {
        if (columnName == "makeID") return Makes.select { Makes.id eq value as Int }.map { fromRow(it) }
        else if (columnName == "name") return Makes.select { Makes.name eq value as String }.map { fromRow(it) }
        return emptyList()
    }

    override fun update(m: Make) {
        Makes.update({Makes.id eq m.id}){
            it[Makes.name] = m.name
        }
    }

    override fun findColumnName(): Iterable<String> {
        var c = arrayListOf<String>()
        Makes.columns.forEach{
            c.add(it.name)
        }
        return c
    }

    private fun toRow(m: Make): Makes.(UpdateBuilder<*>) -> Unit = {
        if (m.id != null) it[id] = m.id
        it[name] = m.name
    }

    private fun fromRow(r: ResultRow) = Make(r[Makes.id], r[Makes.name])

}