package com.github.galbanie.models.crud

import org.jetbrains.exposed.sql.Column

/**
 * Created by Galbanie on 2017-04-21.
 */

interface CrudTable<T> {
    fun createTable()
    fun create(m : T) : T
    fun findAll() : Iterable<T>
    fun deleteAll() : Int
    fun delete(m : T) : Int
    fun findBy(columnName : String, value : Any) : Iterable<T>?
    fun update(m : T)
    fun findColumnName() : Iterable<String>
}