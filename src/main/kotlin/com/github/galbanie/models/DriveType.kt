package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class DriveType(id : Int, driveType : String){
    var id by property(id)
    fun idProperty() = getProperty(DriveType::id)

    var driveType by property(driveType)
    fun driveTypeProperty() = getProperty(DriveType::driveType)
}

class DriveTypeModel : ItemViewModel<DriveType>() {
    val id = bind { item?.idProperty() }
    val driveType = bind { item?.driveTypeProperty() }
}
