package com.github.galbanie.views

import com.github.galbanie.Styles
import com.github.galbanie.ToyotaReferenceScope
import javafx.scene.text.Font
import tornadofx.*

class MainView : View("Toyota Reference - Beta version") {

    override val scope = super.scope as ToyotaReferenceScope

    // Initialisation des References
    override fun onDock() {

    }

    override val root = borderpane {
        top(MenuView::class)

        left{
            vbox {
                addClass(Styles.controlMenu)
                button("Reference") {
                    useMaxWidth = true
                    setOnAction {
                        center(ReferenceView::class)
                    }
                    tooltip("List Vehicles Reference") {
                        font = Font.font("Verdana")
                    }
                }
                button("Manage Data Source") {
                    isDisable = true
                    useMaxWidth = true
                    setOnAction {
                        center(ManageView::class)
                    }
                }
            }
        }

        center(ReferenceView::class)

        bottom {

        }
    }
}