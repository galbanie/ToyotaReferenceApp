package com.github.galbanie.controllers


import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.models.*
import com.github.galbanie.models.crud.*
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*

/**
 * Created by Galbanie on 2017-04-23.
 */

class TableController : Controller(){

    override val scope = super.scope as ToyotaReferenceScope

    val cruds = mapOf(
            "Makes" to CrudMake(),
            "Aspirations" to CrudAspiration()
    )

    init {

    }

    fun init(){
        scope.makes.clear()
        scope.aspirations.clear()
        transaction {
            cruds["Makes"]!!.findAll().forEach { scope.makes.add(it as Make) }
            cruds["Aspirations"]!!.findAll().forEach { scope.aspirations.add(it as Aspiration) }
        }
    }

}