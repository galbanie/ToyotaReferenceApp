package com.github.galbanie.views

import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.utils.Mailto
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.NodeOrientation
import javafx.scene.control.ComboBox
import javafx.scene.control.TextArea
import tornadofx.*

/**
 * Created by Galbanie on 2017-05-02.
 */
class ReportView : View("Report") {

    override val scope  = super.scope as ToyotaReferenceScope

    var typecombobox : ComboBox<String> by singleAssign()
    var messagetextarea : TextArea by singleAssign()
    var exceptiontextarea : TextArea by singleAssign()

    var subject = ""
    var body = ""

    val model = ViewModel()
    val message = model.bind { SimpleStringProperty() }
    val exception = model.bind { SimpleStringProperty() }
    val section = model.bind { SimpleStringProperty() }
    val type = model.bind { SimpleStringProperty() }

    val sections = FXCollections.observableArrayList(
            "References",
            "General"
    )

    val types = FXCollections.observableArrayList(
            "Amelioration",
            "Error",
            "Data",
            "Bug"
    )

    init {

    }

    override val root = vbox {

        form {
            fieldset {
                field("Sections") {
                    combobox<String>(section) {
                        items = sections
                        selectionModel.selectLast()

                    }
                }
                field("Types") {
                    typecombobox = combobox<String>(type) {
                        items = types
                        selectionModel.selectFirst()
                        setOnAction {

                        }
                    }
                }
            }
            fieldset {
                field("Exception") {
                    exceptiontextarea = textarea(exception) {

                    }
                }
                field("Message") {
                    messagetextarea = textarea(message) {
                        required()
                    }
                }
            }
        }

        toolbar{
            nodeOrientation = NodeOrientation.RIGHT_TO_LEFT
            button("Send") {
                enableWhen { model.valid }
                setOnAction {
                    model.commit {
                        subject = section.value + " [" + type.value + "]"
                        body = exception.value + "\n" + message.value
                        Mailto().mailto(listOf("galbanie.dev@gmail.com"), subject, body)
                        close()
                    }
                }
            }
            button("Cancel") {
                setOnAction {
                    close()
                }
            }
        }

    }
}
