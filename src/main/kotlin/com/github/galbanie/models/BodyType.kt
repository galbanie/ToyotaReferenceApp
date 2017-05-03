package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-23.
 */

class BodyType( id : Int, bodyType : String){
    var id by property(id)
    fun idProperty() = getProperty(BodyType::id)

    var bodyType by property(bodyType)
    fun bodyTypeProperty() = getProperty(BodyType::bodyType)
}

class BodyTypeModel : ItemViewModel<BodyType>() {
    val id = bind { item?.idProperty() }
    val bodyType = bind { item?.bodyTypeProperty() }
}
