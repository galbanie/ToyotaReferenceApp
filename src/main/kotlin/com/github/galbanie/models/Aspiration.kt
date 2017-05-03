package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-23.
 */

class Aspiration(id : Int, aspiration : String){
    var id by property(id)
    fun idProperty() = getProperty(Aspiration::id)

    var aspiration by property(aspiration)
    fun aspirationProperty() = getProperty(Aspiration::aspiration)
}

class AspirationModel : ItemViewModel<Aspiration>() {
    val id = bind { item?.idProperty() }
    val aspiration = bind { item?.aspirationProperty() }
}
