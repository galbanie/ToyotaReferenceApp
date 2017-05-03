package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class Liter(id : Int, liter : String){
    var id by property(id)
    fun idProperty() = getProperty(Liter::id)

    var liter by property(liter)
    fun literProperty() = getProperty(Liter::liter)
}

class LiterModel : ItemViewModel<Liter>() {
    val id = bind { item?.idProperty() }
    val liter = bind { item?.literProperty() }
}
