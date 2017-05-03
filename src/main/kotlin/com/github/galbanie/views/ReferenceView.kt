package com.github.galbanie.views


import com.github.galbanie.controllers.ReferenceController
import tornadofx.*

/**
 * Created by Galbanie on 2017-05-02.
 */
class ReferenceView : View() {

        val refctrl: ReferenceController by inject()

        init {
            refctrl.loadAsync { refctrl.loadAllReference() }
        }

        override val root = borderpane {
            style {
                center {
                    padding = insets(5.0)
                }
            }
            top(HeaderRefView::class)
            with(refctrl.viewtype) {
                onChange {
                    if (refctrl.viewtype.get() == "List") center(ReferenceList::class)
                    else center(ReferenceTable::class)
                }
            }
            center(ReferenceList::class)
            bottom(FooterRefView::class)
        }
}
