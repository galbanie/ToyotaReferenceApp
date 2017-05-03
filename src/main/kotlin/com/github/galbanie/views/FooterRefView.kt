package com.github.galbanie.views

import com.github.galbanie.controllers.ReferenceController
import javafx.geometry.Pos
import tornadofx.*

/**
 * Created by Galbanie on 2017-04-14.
 */
class FooterRefView : View() {

    val refctrl : ReferenceController by inject()

    val items = integerBinding(refctrl.references.items) { count { true } }

    override val root = hbox(5.0) {
        //addClass(Styles.footer)
        style{
            padding = box(10.px)
            alignment = Pos.CENTER
            spacing = 20.px
        }
        label(stringBinding(items) { "$value reference${value.plural}" })
    }
    val Int.plural: String get() = if (this <= 1) "" else "s"
}
