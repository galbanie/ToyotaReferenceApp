package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class Model(id : Int, name : String){
    var id by property(id)
    fun idProperty() = getProperty(Model::id)

    var name by property(name)
    fun nameProperty() = getProperty(Model::name)
}

class ModelModel : ItemViewModel<Model>() {
    val id = bind { item?.idProperty() }
    val name = bind { item?.nameProperty() }
}
