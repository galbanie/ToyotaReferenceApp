package com.github.galbanie.controllers

import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.models.Reference
import com.github.galbanie.models.db.*
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*

/**
 * Created by Galbanie on 2017-04-14.
 */

class ReferenceController : Controller(){

    override val scope = super.scope as ToyotaReferenceScope

    val viewtype = SimpleStringProperty("List")

    val references = SortedFilteredList<Reference>()
    val makes = FXCollections.observableArrayList<String>("(Select Make)")
    val models = FXCollections.observableArrayList<String>("(Select Model)")
    val years = FXCollections.observableArrayList<String>("(Select Year)")
    val liters = FXCollections.observableArrayList<String>("(Select Liter)")
    val chassisCodes = FXCollections.observableArrayList<String>("(Select Code)")

    val referenceStatement = (
            References
            innerJoin Makes
            innerJoin Models
            innerJoin Years
            innerJoin Chassis
            innerJoin Others
            innerJoin EngineLiters
            innerJoin Aspirations
            innerJoin DriveTypes
            innerJoin NumDoors
            innerJoin BodyTypes
            innerJoin FuelTypes
            innerJoin SubModels
            innerJoin EngineCodes
            innerJoin Comments
            ).slice(
            References.id,
            Makes.name,
            Models.name,
            Years.year,
            Chassis.code,
            Others.other,
            EngineLiters.liter,
            Aspirations.aspiration,
            DriveTypes.driveType,
            NumDoors.numDoor,
            BodyTypes.bodyType,
            FuelTypes.fuelType,
            SubModels.name,
            EngineCodes.code,
            Comments.comment
    )

    init {
        scope.db
        //loadAsync { loadAllReference() }
        loadMake()
    }

    fun loadMake(){
        transaction{
            Makes.slice(Makes.name).selectAll().mapTo(makes) { it[Makes.name] }
        }
    }

    fun loadModel(make : String){
        models.setAll(models.first())
        transaction {
            (References innerJoin Makes innerJoin Models).slice(Models.name).select {Makes.name.eq(make)}.withDistinct().orderBy(Models.name, true).forEach {
                models.add(it[Models.name])
            }
        }
        models.setAll(models.distinct())
    }

    fun loadYear(make : String, model : String){
        years.setAll(years.first())
        transaction {
            (References innerJoin Makes innerJoin Models innerJoin Years).slice(Years.year).select { Makes.name.eq(make) and Models.name.eq(model) }.withDistinct().orderBy(Years.year, false).forEach {
                years.add(it[Years.year])
            }
        }
        years.setAll(years.distinct())
    }

    fun loadLiter(make: String, model: String, year: String){
        liters.setAll(liters.first())
        transaction {
            (References innerJoin Makes innerJoin Models innerJoin Years innerJoin EngineLiters).select {Makes.name.eq(make) and Models.name.eq(model) and Years.year.eq(year)}.withDistinct().orderBy(EngineLiters.liter, true).forEach {
                liters.add(it[EngineLiters.liter])
            }
        }
        liters.setAll(liters.distinct())
    }

    fun loadChassisCode(make : String, model : String, year : String, liter : String){
        chassisCodes.setAll(chassisCodes.first())
        transaction {
            (References innerJoin Makes innerJoin Models innerJoin Years innerJoin EngineLiters innerJoin Chassis).select {Makes.name.eq(make) and Models.name.eq(model) and Years.year.eq(year) and EngineLiters.liter.eq(liter)}.withDistinct().orderBy(Chassis.code, true).forEach {
                chassisCodes.add(it[Chassis.code])
            }
        }
        chassisCodes.setAll(chassisCodes.distinct())
    }

    fun loadAllReference() : List<Reference>{
        val refs = arrayListOf<Reference>()
        transaction {
            referenceStatement.selectAll().forEach {
                refs.add(Reference(
                        it[References.id].toString(),it[Makes.name], it[Models.name], it[Years.year], it[Chassis.code], it[Others.other],
                        it[EngineLiters.liter], it[Aspirations.aspiration], it[DriveTypes.driveType], it[NumDoors.numDoor],
                        it[BodyTypes.bodyType], it[FuelTypes.fuelType], it[SubModels.name], it[EngineCodes.code], it[Comments.comment]
                ))
            }
        }
        return refs
    }

    fun loadReferenceBy(make : String, model : String? = null, year : String? = null,liter : String? = null, chassisCode : String? = null) : List<Reference>{
        val refs = arrayListOf<Reference>()
        transaction {
            var statement = referenceStatement.select{Makes.name.eq(make)}
            if(model != null){
                if (year != null){
                    if(liter != null){
                        if(chassisCode != null){
                            statement = referenceStatement.select{Makes.name.eq(make) and Models.name.eq(model) and Years.year.eq(year) and EngineLiters.liter.eq(liter) and Chassis.code.eq(chassisCode)}
                        }else statement = referenceStatement.select{Makes.name.eq(make) and Models.name.eq(model) and Years.year.eq(year) and EngineLiters.liter.eq(liter) }
                    }
                    else statement = referenceStatement.select{Makes.name.eq(make) and Models.name.eq(model) and Years.year.eq(year) }
                }else statement = referenceStatement.select{Makes.name.eq(make) and Models.name.eq(model)}
            }
            statement.forEach {
                refs.add(Reference(
                        it[References.id].toString(),it[Makes.name], it[Models.name], it[Years.year], it[Chassis.code], it[Others.other],
                        it[EngineLiters.liter], it[Aspirations.aspiration], it[DriveTypes.driveType], it[NumDoors.numDoor],
                        it[BodyTypes.bodyType], it[FuelTypes.fuelType], it[SubModels.name], it[EngineCodes.code], it[Comments.comment]
                ))
            }
        }
        return refs
    }

    fun loadAsync(function : () -> List<Reference>){
        val refs = FXCollections.observableArrayList<Reference>()
        runAsync {
            updateMessage("Loading Reference")
            updateProgress(0.4, 1.0)
            refs.addAll(function())
        } ui {
            references.setAll(refs)
        }
    }

    fun loadSync(function : () -> List<Reference>){
        val refs = FXCollections.observableArrayList<Reference>()
        run {
            refs.addAll(function())
            references.setAll(refs)
        }
    }
}