package com.github.galbanie.models.db


import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Created by Galbanie on 2017-04-15.
 */

object Makes : Table("Makes"){
    val id = integer("makeID").primaryKey()
    val name = varchar("name",length = 100).uniqueIndex()
}

object Models : Table("Models"){
    val id = integer("modelID").primaryKey()
    val name = varchar("name", length = 100).uniqueIndex()
}

object Years : Table("Years"){
    val id = integer("yearID").primaryKey()
    val year = varchar("year", length = 50)
}

object Chassis : Table("Chassis"){
    val id = integer("chassisID").primaryKey()
    val code = varchar("code", length = 50).uniqueIndex()
}

object Others : Table("Others"){
    val id = integer("otherID").primaryKey()
    val other = varchar("other", length = 150).uniqueIndex()
}

object EngineLiters : Table("Liters"){
    val id = integer("literID").primaryKey()
    val liter = varchar("liter", length = 50)
}

object Aspirations : Table("Aspirations"){
    val id = integer("aspirationID").primaryKey()
    val aspiration = varchar("aspiration", length = 100).uniqueIndex()
}
object DriveTypes : Table("DriveTypes"){
    val id = integer("driveTypeID").primaryKey()
    val driveType = varchar("driveType", length = 100).uniqueIndex()
}

object BodyTypes : Table("BodyTypes"){
    val id = integer("bodyTypeID").primaryKey()
    val bodyType = varchar("bodyType", length = 100).uniqueIndex()
}

object NumDoors : Table("NumDoors"){
    val id = integer("numDoorID").primaryKey()
    val numDoor = varchar("numDoor", length = 50).uniqueIndex()
}

object EngineCodes : Table("EngineCodes"){
    val id = integer("engineCodeID").primaryKey()
    val code = varchar("code", length = 100).uniqueIndex()
}

object FuelTypes : Table("FuelTypes"){
    val id = integer("fuelTypeID").primaryKey()
    val fuelType = varchar("fuelType", length = 100).uniqueIndex()
}

object SubModels : Table("SubModels"){
    val id = integer("subModelID").primaryKey()
    val name = varchar("name", length = 100).uniqueIndex()
}

object Comments : Table("Comments"){
    val id = integer("commentID").primaryKey()
    val comment = varchar("comment", length = 700).uniqueIndex()
}

object References : Table("References"){
    val id = integer("referenceID").primaryKey()
    val makeID = integer("makeID") references Makes.id
    val modelID = integer("modelID") references Models.id
    val yearID = integer("yearID") references Years.id
    val chassisID = (integer("chassisID") references Chassis.id).nullable()
    val otherID = (integer("otherID") references Others.id).nullable()
    val literID = (integer("literID") references EngineLiters.id).nullable()
    val aspirationID = (integer("aspirationID") references Aspirations.id).nullable()
    val driveTypeID = (integer("driveTypeID") references DriveTypes.id).nullable()
    val numDoorID = (integer("numDoorID") references NumDoors.id).nullable()
    val bodyTypeID = (integer("bodyTypeID") references BodyTypes.id).nullable()
    val fuelTypeID = (integer("fuelTypeID") references FuelTypes.id).nullable()
    val subModelID = (integer("subModelID") references SubModels.id).nullable()
    val engineCodeID = (integer("engineCodeID") references EngineCodes.id).nullable()
    val commentID = (integer("commentID") references Comments.id).nullable()
}

fun createTables(){
    //Database.connect("jdbc:h2:file:./db/data;IFEXISTS=TRUE",driver = "org.h2.Driver")
    //Database.connect("jdbc:h2:file:./db/database;DB_CLOSE_DELAY=-1;IFEXISTS=TRUE",driver = "org.h2.Driver")
    Database.connect("jdbc:h2:file:./db/tr_db;DB_CLOSE_DELAY=-1;IFEXISTS=TRUE",driver = "org.h2.Driver")

    transaction {
        create<Table>(Makes,Models,Years,Chassis,Others,EngineLiters,Aspirations,DriveTypes,BodyTypes,NumDoors,EngineCodes,FuelTypes,SubModels,Comments,References)
    }
}

fun main(args: Array<String>) {
    Database.connect("jdbc:h2:file:./db/tr_db;DB_CLOSE_DELAY=-1;IFEXISTS=TRUE",driver = "org.h2.Driver")

    /*val referenceStatement = (
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

    var make = "Scion"
    var models = ""*/

    transaction {

        //create<Table>(Makes,Models,Years,Chassis,Others,EngineLiters,Aspirations,DriveTypes,BodyTypes,NumDoors,EngineCodes,FuelTypes,SubModels,Comments,References)

        /*referenceStatement.select {Makes.name.eq(make)}.orderBy(Models.name, true).withDistinct().forEach {
            println(it[Models.name])
        }

        (References innerJoin Makes innerJoin Models).slice(Models.name).select {Makes.name.eq(make)}.withDistinct().orderBy(Models.name, true).forEach {
            println(it[Models.name])
        }*/

        /*referenceStatement.select{Makes.name.eq(make) and Models.name.eq(models) }.forEach {
            println("""${it[References.id].toString()}, ${it[Makes.name]}, ${it[Models.name]}, ${it[Years.year]}, ${it[Chassis.code]}""")
        }*/

        //println(Makes.columns[0].name)

    }
}