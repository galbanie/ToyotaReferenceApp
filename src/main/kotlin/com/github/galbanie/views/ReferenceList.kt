package com.github.galbanie.views


import com.github.galbanie.controllers.ReferenceController
import javafx.geometry.Orientation
import javafx.geometry.Pos
import tornadofx.*

/**
 * Created by Galbanie on 2017-04-14.
 */
class ReferenceList : View() {

    val refctrl : ReferenceController by inject()

    override val root = listview(refctrl.references) {
        //cellFragment(ReferenceItemFragment::class)
        cellCache {
            hbox {
                useMaxWidth = true
                form {
                    fieldset("Info"){
                        field("Make :") {
                            textfield(it.make){
                                isEditable = false
                            }
                        }
                        field("Model :") {
                            textfield(it.model){
                                isEditable = false
                            }
                        }
                        field("Year :") {
                            textfield(it.year){
                                isEditable = false
                            }
                        }
                    }
                    fieldset("Chassis") {
                        field("Code :") {
                            textfield(it.chassisCode){
                                isEditable = false
                            }
                        }
                        field("Other :") {
                            textfield(it.other){
                                isEditable = false
                            }
                        }
                    }
                }
                form {
                    fieldset("Engine"){
                        field("Liter :") {
                            textfield(it.engineLiter){
                                isEditable = false
                            }
                        }
                        field("Code :") {
                            textfield(it.engineCode){
                                isEditable = false
                            }
                        }
                        field("Aspiration :") {
                            textfield(it.aspiration){
                                isEditable = false
                            }
                        }
                    }
                    fieldset("Body") {
                        field("BodyType :") {
                            textfield(it.bodyType){
                                isEditable = false
                            }
                        }
                        field("DriveType :") {
                            textfield(it.driveType){
                                isEditable = false
                            }
                        }
                    }

                }

                vbox {
                    hbox {
                        alignment = Pos.CENTER_LEFT
                        form {
                            style {
                                padding = box(top = 10.px, bottom = 0.px, left = 10.px, right = 10.px)
                            }
                            fieldset("Other"){
                                field("Num Door :") {
                                    textfield(it.numDoor){
                                        isEditable = false
                                    }
                                }
                                field("FuelType :") {
                                    textfield(it.fuelType){
                                        isEditable = false
                                    }
                                }
                                field("SubModel :") {
                                    textfield(it.submodel){
                                        isEditable = false
                                    }
                                }

                            }
                        }
                        imageview {
                            image = if (ReferenceList::class.java.getResource("/image/${it.chassisCode}.gif") != null) resources.image("/image/${it.chassisCode}.gif") else resources.image("/image/_comingsoon.gif")
                        }
                    }

                    form {
                        style {
                            padding = box(top = 0.px, bottom = 10.px, left = 10.px, right = 10.px)
                        }
                        fieldset("Comment") {
                            labelPosition = Orientation.VERTICAL
                            field() {
                                textarea{
                                    prefHeight = 55.0
                                    useMaxWidth = false
                                    isEditable = false
                                    isWrapText = true
                                    text = it.comment
                                }
                            }
                        }
                    }

                }

            }

        }

    }
}
