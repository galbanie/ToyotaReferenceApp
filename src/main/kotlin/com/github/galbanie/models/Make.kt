package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-22.
 */

class Make ( id : Int, name : String){
    var id by property(id)
    fun idProperty() = getProperty(Make::id)

    var name by property(name)
    fun nameProperty() = getProperty(Make::name)
}

class MakeModel : ItemViewModel<Make>() {
    val id = bind { item?.idProperty() }
    val name = bind { item?.nameProperty() }
}
