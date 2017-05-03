package com.github.galbanie.views

import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.controllers.ReferenceController
import com.github.galbanie.controllers.TableController
import com.github.galbanie.views.manage.*
import javafx.geometry.Orientation
import javafx.scene.control.ButtonBar
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

/**
 * Created by Galbanie on 2017-05-02.
 */
class ManageView : View() {

    override val scope = super.scope as ToyotaReferenceScope

    val refctrl : ReferenceController by inject()
    val tablectrl : TableController by inject()

    lateinit var tableList : ListView<String>
    lateinit var pane : BorderPane
    lateinit var lab : Label

    val tableviews = mapOf(
            "Makes" to TableViewMake::class,
            "Aspirations" to TableViewAspiration::class
    )

    init {
        tablectrl.init()
    }

    override val root = vbox {
        buttonbar {
            style{
                padding = box(10.px)
            }
            button("Commit", ButtonBar.ButtonData.APPLY).setOnAction {

            }
            button("Rollback", ButtonBar.ButtonData.BACK_PREVIOUS).setOnAction {

            }
        }
        splitpane(){
            orientation = Orientation.HORIZONTAL
            tableList = listview(scope.tables) {
                bindSelected(scope.tableSelected)
                onUserSelect(1){
                    println(scope.tableSelected!!)
                    println(tableviews[scope.tableSelected.value])
                    //lab = label("  ${scope.tableSelected.value!!}")
                    pane.center(tableviews[scope.tableSelected.value]!!)
                }
            }
            pane = borderpane {
                top{
                    vbox {
                        lab = label(scope.tableSelected){
                            style{
                                fontSize = 1.5.em
                                fontWeight = FontWeight.BOLD
                                textFill = c(0, 56, 101, 1.00)
                                backgroundColor += Color.WHITE
                                //borderColor += Color.BLACK
                            }
                            useMaxWidth = true
                        }
                        form {
                            fieldset {
                                field("Filters", orientation = Orientation.HORIZONTAL,forceLabelIndent = true){
                                    textfield {

                                    }
                                    combobox<String> {

                                    }
                                    button("Execute") {
                                        setOnAction {

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                center(tableviews[scope.tableSelected.value]!!)
            }
        }
        buttonbar {
            label(" # rows")
        }
    }
}
