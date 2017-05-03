package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class FuelType(id : Int, fuelType: String){
    var id by property(id)
    fun idProperty() = getProperty(FuelType::id)

    var fuelType by property(fuelType)
    fun fuelTypeProperty() = getProperty(FuelType::fuelType)
}

class FuelTypeModel : ItemViewModel<FuelType>() {
    val id = bind { item?.idProperty() }
    val fuelType = bind { item?.fuelTypeProperty() }
}
