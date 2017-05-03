package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class Chassis(id : Int, code : String){
    var id by property(id)
    fun idProperty() = getProperty(Chassis::id)

    var code by property(code)
    fun codeProperty() = getProperty(Chassis::code)
}

class ChassisModel : ItemViewModel<Chassis>() {
    val id = bind { item?.idProperty() }
    val code = bind { item?.codeProperty() }
}
