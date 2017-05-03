package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class EngineCode(id : Int, code : String){
    var id by property(id)
    fun idProperty() = getProperty(EngineCode::id)

    var code by property(code)
    fun codeProperty() = getProperty(EngineCode::code)
}

class EngineCodeModel : ItemViewModel<EngineCode>() {
    val id = bind { item?.idProperty() }
    val code = bind { item?.codeProperty() }
}
