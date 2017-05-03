package com.github.galbanie.views.manage


import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.controllers.TableController
import com.github.galbanie.models.Aspiration
import tornadofx.*
import javafx.scene.control.TableView

/**
 * Created by Galbanie on 2017-04-23.
 */
class TableViewAspiration : View() {

    override val scope = super.scope as ToyotaReferenceScope

    val tablectrl : TableController by inject()

    lateinit var tableViewEditModel : TableViewEditModel<Aspiration>

    override val root = TableView(scope.aspirations)

    init {
        with(root){
            isEditable = true

            column("AspirationID", Aspiration::id)
            column("Aspiration", Aspiration::aspiration)

            tableViewEditModel = editModel
        }
    }
}
