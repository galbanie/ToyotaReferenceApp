package com.github.galbanie.views.manage


import com.github.galbanie.ToyotaReferenceScope
import com.github.galbanie.controllers.TableController
import com.github.galbanie.models.Make
import tornadofx.*
import javafx.scene.control.TableView

/**
 * Created by Galbanie on 2017-04-23.
 */
class TableViewMake : View() {

    override val scope = super.scope as ToyotaReferenceScope

    val tablectrl : TableController by inject()

    lateinit var tableViewEditModel : TableViewEditModel<Make>

    override val root = TableView(scope.makes)

    init {
        with(root){
            isEditable = true

            column("MakeID", Make::id)
            column("Name", Make::name).makeEditable()

            enableCellEditing()
            enableDirtyTracking()

            tableViewEditModel = editModel
        }
    }
}
