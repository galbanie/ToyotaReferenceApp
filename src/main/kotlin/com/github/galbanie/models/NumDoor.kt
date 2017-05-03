package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class NumDoor(id : Int, numDoor : String){
    var id by property(id)
    fun idProperty() = getProperty(NumDoor::id)

    var numDoor by property(numDoor)
    fun numDoorProperty() = getProperty(NumDoor::numDoor)
}

class NumDoorModel : ItemViewModel<NumDoor>() {
    val id = bind { item?.idProperty() }
    val numDoor = bind { item?.numDoorProperty() }
}
