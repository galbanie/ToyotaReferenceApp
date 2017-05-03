package com.github.galbanie

import com.github.galbanie.models.*
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import org.jetbrains.exposed.sql.Database
import tornadofx.*

/**
 * Created by Galbanie on 2017-04-14.
 */

class ToyotaReferenceScope : Scope(){
    val versions = mapOf(
            "app" to 1.0,
            "data" to 1.790,
            "res" to 1.0
    )
    val db = Database.connect("jdbc:h2:file:./db/tr_db;DB_CLOSE_DELAY=-1;IFEXISTS=TRUE",driver = "org.h2.Driver")

    val tables = FXCollections.observableArrayList(
            "Aspirations",
            "BodyTypes",
            "Chassis",
            "Orders",
            "Comments",
            "DriveTypes",
            "EngineCodes",
            "FuelTypes",
            "Liters",
            "Makes",
            "Models",
            "NumDoors",
            "References",
            "SubModels",
            "Years"
    )

    val modelview = ViewModel()

    val tableSelected = modelview.bind{SimpleStringProperty("Makes")}

    /*val items = mapOf(
            "Makes" to FXCollections.observableArrayList<Make>(),
            "Aspirations" to FXCollections.observableArrayList<Aspiration>()
    )*/

    val makes = FXCollections.observableArrayList<Make>()
    val aspirations = FXCollections.observableArrayList<Aspiration>()

}