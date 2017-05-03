package com.github.galbanie.views

import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.controllers.ReferenceController
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.ComboBox
import javafx.scene.layout.Border
import javafx.scene.paint.Color
import tornadofx.*

/**
 * Created by Galbanie on 2017-04-14.
 */
class HeaderRefView : View() {

    override val scope = super.scope as ToyotaReferenceScope

    val refctrl : ReferenceController by inject()

    val make = scope.modelview.bind { SimpleStringProperty() }
    val model = scope.modelview.bind { SimpleStringProperty() }
    val year = scope.modelview.bind { SimpleStringProperty() }
    val chassiscode = scope.modelview.bind { SimpleStringProperty() }

    lateinit var makecombobox : ComboBox<String>
    lateinit var modelcombobox : ComboBox<String>
    lateinit var yearcombobox : ComboBox<String>
    lateinit var chassiscombobox : ComboBox<String>

    override val root = vbox {

        form {
            fieldset {
                labelPosition = Orientation.VERTICAL
                field("Search") {
                    textfield() {
                        refctrl.references.filterWhen(textProperty(), { query, item -> item.matches(query) })
                    }
                }
            }
            fieldset {
                labelPosition = Orientation.VERTICAL
                field("Filters", orientation = Orientation.HORIZONTAL,forceLabelIndent = true) {
                    makecombobox = combobox(make) {
                        items = refctrl.makes
                        selectionModel.selectFirst()
                        setOnAction {
                            yearcombobox.selectionModel.selectFirst()
                            modelcombobox.selectionModel.selectFirst()
                            chassiscombobox.selectionModel.selectFirst()
                            refctrl.loadModel(selectedItem!!)
                            // load async Reference by make
                            if (!selectedItem.equals(refctrl.makes.first())) refctrl.loadSync { refctrl.loadReferenceBy(selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadAllReference() }
                        }
                    }

                    modelcombobox = combobox(model) {
                        items = refctrl.models
                        selectionModel.selectFirst()
                        setOnAction {
                            yearcombobox.selectionModel.selectFirst()
                            chassiscombobox.selectionModel.selectFirst()
                            refctrl.loadYear(makecombobox.selectedItem!!, selectedItem!!)
                            // load async Reference by make and model
                            if (!selectedItem.equals(refctrl.models.first())) refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!) }
                        }
                    }

                    yearcombobox = combobox(year) {
                        items = refctrl.years
                        selectionModel.selectFirst()
                        setOnAction {
                            chassiscombobox.selectionModel.selectFirst()
                            refctrl.loadChassisCode(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, selectedItem!!)
                            // load async Reference by make, model and year
                            if (!selectedItem.equals(refctrl.years.first())) refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!) }
                        }
                    }

                    chassiscombobox = combobox(chassiscode) {
                        items = refctrl.chassisCodes
                        selectionModel.selectFirst()
                        setOnAction {
                            // load async Reference by make, model, year and chassis code

                            if (!selectedItem.equals(refctrl.chassisCodes.first())) refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, yearcombobox.selectedItem!!, selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, yearcombobox.selectedItem!!) }
                        }
                    }

                    button("Clear") {
                        setOnAction{
                            makecombobox.selectionModel.selectFirst()
                            modelcombobox.selectionModel.selectFirst()
                            yearcombobox.selectionModel.selectFirst()
                            chassiscombobox.selectionModel.selectFirst()
                            refctrl.loadSync { refctrl.loadAllReference() }
                        }
                    }
                }
            }
        }

        toolbar{
            style {
                backgroundColor += Color.TRANSPARENT
                border = Border.EMPTY
            }

            togglegroup {
                togglebutton {
                    graphic = FontAwesomeIconView(FontAwesomeIcon.LIST_ALT)
                    setOnAction {

                    }
                }.whenSelected { refctrl.viewtype.set("List")  }
                togglebutton {
                    graphic = FontAwesomeIconView(FontAwesomeIcon.TABLE)
                    setOnAction {

                    }
                }.whenSelected { refctrl.viewtype.set("Table") }
            }
        }

    }
}
