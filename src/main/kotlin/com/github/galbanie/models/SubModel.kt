package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class SubModel(id : Int, name : String){
    var id by property(id)
    fun idProperty() = getProperty(SubModel::id)

    var name by property(name)
    fun nameProperty() = getProperty(SubModel::name)
}

class SubModelModel : ItemViewModel<SubModel>() {
    val id = bind { item?.idProperty() }
    val name = bind { item?.nameProperty() }
}
