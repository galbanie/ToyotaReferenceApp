package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class Year(id : Int, year : String){
    var id by property(id)
    fun idProperty() = getProperty(Year::id)

    var year by property(year)
    fun yearProperty() = getProperty(Year::year)
}

class YearModel : ItemViewModel<Year>() {
    val id = bind { item?.idProperty() }
    val year = bind { item?.yearProperty() }
}
