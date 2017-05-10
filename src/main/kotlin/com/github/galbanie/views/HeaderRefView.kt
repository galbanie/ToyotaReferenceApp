package com.github.galbanie.views

import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.controllers.ReferenceController
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.ComboBox
import javafx.scene.input.KeyCode
import javafx.scene.layout.Border
import javafx.scene.paint.Color
import tornadofx.*
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timerTask

/**
 * Created by Galbanie on 2017-04-14.
 */
class HeaderRefView : View() {

    override val scope = super.scope as ToyotaReferenceScope

    val refctrl : ReferenceController by inject()

    val make = scope.modelview.bind { SimpleStringProperty() }
    val model = scope.modelview.bind { SimpleStringProperty() }
    val year = scope.modelview.bind { SimpleStringProperty() }
    val liter = scope.modelview.bind { SimpleStringProperty() }
    val chassiscode = scope.modelview.bind { SimpleStringProperty() }

    lateinit var makecombobox : ComboBox<String>
    lateinit var modelcombobox : ComboBox<String>
    lateinit var yearcombobox : ComboBox<String>
    lateinit var litercombobox : ComboBox<String>
    lateinit var chassiscombobox : ComboBox<String>

    var sb = ""

    /*var fixedRateTimer = fixedRateTimer(name = "reset-sb",
            initialDelay = 2000, period = 2000) {
        println("@")
        sb = ""
    }*/

    val ttsb = timerTask {
        println("@")
        sb = ""
    }

    init {

    }


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
                            litercombobox.selectionModel.selectFirst()
                            chassiscombobox.selectionModel.selectFirst()
                            refctrl.loadModel(selectedItem!!)
                            // load async Reference by make
                            if (!selectedItem.equals(refctrl.makes.first())) refctrl.loadSync { refctrl.loadReferenceBy(selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadAllReference() }
                        }
                        setOnKeyReleased {
                            /*var s = jumpTo(it.character,selectedItem,items)
                            if (s != null){
                                //value = s
                                selectionModel.select(s)
                            }*/
                            if( it.code == KeyCode.BACK_SPACE && sb.length > 0 )
                                sb = sb.substring( 0, sb.length - 1 )
                            else if(it.code != KeyCode.TAB){
                                sb += it.text
                            }

                            if(sb.length == 0) {
                                selectionModel.selectFirst()
                                return@setOnKeyReleased
                            }

                            for(item in items) {
                                if (item.toLowerCase().startsWith(sb.toLowerCase())){
                                    selectionModel.select(item)
                                    return@setOnKeyReleased
                                }
                            }
                        }
                        setOnMouseMoved { sb = "" }
                        setOnHiding { sb = "" }
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
                        setOnKeyReleased {
                            //println(sb)
                            if( it.code == KeyCode.BACK_SPACE && sb.length > 0 )
                                sb = sb.substring( 0, sb.length - 1 )
                            else if(it.code != KeyCode.TAB){
                                sb += it.text
                            }

                            if(sb.length == 0) {
                                selectionModel.selectFirst()
                                return@setOnKeyReleased
                            }

                            for(item in items) {
                                if (item.toLowerCase().startsWith(sb.toLowerCase())){
                                    selectionModel.select(item)
                                    return@setOnKeyReleased
                                }
                            }
                        }
                        setOnMouseMoved { sb = "" }
                        setOnHiding { sb = "" }
                    }

                    yearcombobox = combobox(year) {
                        items = refctrl.years
                        selectionModel.selectFirst()
                        setOnAction {
                            litercombobox.selectionModel.selectFirst()
                            chassiscombobox.selectionModel.selectFirst()
                            refctrl.loadLiter(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, selectedItem!!)
                            // load async Reference by make, model and year
                            if (!selectedItem.equals(refctrl.years.first())) refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!) }
                        }
                        setOnKeyReleased {
                            if( it.code == KeyCode.BACK_SPACE && sb.length > 0 )
                                sb = sb.substring( 0, sb.length - 1 )
                            else if(it.code != KeyCode.TAB){
                                sb += it.text
                            }

                            if(sb.length == 0) {
                                selectionModel.selectFirst()
                                return@setOnKeyReleased
                            }

                            for(item in items) {
                                if (item.toLowerCase().startsWith(sb.toLowerCase())){
                                    selectionModel.select(item)
                                    return@setOnKeyReleased
                                }
                            }
                        }
                        setOnMouseMoved { sb = "" }
                        setOnHiding { sb = "" }
                    }

                    litercombobox = combobox(liter) {
                        items = refctrl.liters
                        selectionModel.selectFirst()
                        setOnAction {
                            chassiscombobox.selectionModel.selectFirst()
                            refctrl.loadChassisCode(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, yearcombobox.selectedItem!!, selectedItem!!)
                            if (!selectedItem.equals(refctrl.liters.first())) refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, yearcombobox.selectedItem!!, selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, yearcombobox.selectedItem!!) }
                        }
                        setOnKeyReleased {
                            if( it.code == KeyCode.BACK_SPACE && sb.length > 0 )
                                sb = sb.substring( 0, sb.length - 1 )
                            else if(it.code != KeyCode.TAB){
                                sb += it.text
                            }

                            if(sb.length == 0) {
                                selectionModel.selectFirst()
                                return@setOnKeyReleased
                            }

                            for(item in items) {
                                if (item.toLowerCase().startsWith(sb.toLowerCase())){
                                    selectionModel.select(item)
                                    return@setOnKeyReleased
                                }
                            }
                        }
                        setOnMouseMoved { sb = "" }
                        setOnHiding { sb = "" }
                    }

                    chassiscombobox = combobox(chassiscode) {
                        items = refctrl.chassisCodes
                        selectionModel.selectFirst()
                        setOnAction {
                            if (!selectedItem.equals(refctrl.chassisCodes.first())) refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, yearcombobox.selectedItem!!, litercombobox.selectedItem!!,selectedItem!!) }
                            else refctrl.loadSync { refctrl.loadReferenceBy(makecombobox.selectedItem!!, modelcombobox.selectedItem!!, yearcombobox.selectedItem!!, litercombobox.selectedItem!!) }
                        }
                        setOnKeyReleased {
                            if( it.code == KeyCode.BACK_SPACE && sb.length > 0 )
                                sb = sb.substring( 0, sb.length - 1 )
                            else if(it.code != KeyCode.TAB){
                                sb += it.text
                            }

                            if(sb.length == 0) {
                                selectionModel.selectFirst()
                                return@setOnKeyReleased
                            }

                            for(item in items) {
                                if (item.toLowerCase().startsWith(sb.toLowerCase())){
                                    selectionModel.select(item)
                                    return@setOnKeyReleased
                                }
                            }
                        }
                        setOnMouseMoved { sb = "" }
                        setOnHiding { sb = "" }
                    }

                    button("Clear") {
                        setOnAction{
                            makecombobox.selectionModel.selectFirst()
                            modelcombobox.selectionModel.selectFirst()
                            yearcombobox.selectionModel.selectFirst()
                            litercombobox.selectionModel.selectFirst()
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

    fun jumpTo(keyPressed : String, currentlySelected : String?, items : List<String>) : String?{
        var key = keyPressed.toUpperCase()
        if (key.matches(Regex("^[a-zA-Z_0-9]$"))){
            var letterFound = false;
            var foundCurrent = currentlySelected == null
            for(s in items){
                if (s.toUpperCase().startsWith(key)) {
                    letterFound = true
                    if (foundCurrent) {
                        return s
                    }
                    foundCurrent = s.equals(currentlySelected)
                }
            }
            if (letterFound) {
                return jumpTo(keyPressed, null, items)
            }
        }
        return null
    }

}
