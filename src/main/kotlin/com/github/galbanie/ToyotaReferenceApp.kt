package com.github.galbanie

import com.github.galbanie.models.db.createTables
import com.github.galbanie.views.MainView
import javafx.application.Application
import tornadofx.*

class ToyotaReferenceApp: App(MainView::class, Styles::class, ToyotaReferenceScope()){
    init {
        reloadStylesheetsOnFocus()
        createTables()
        setStageIcon(resources.image("/icons/favicon.ico"), ToyotaReferenceScope())
    }
}

/**
 * The main method is needed to support the mvn jfx:run goal.
 */
fun main(args: Array<String>) {
    Application.launch(ToyotaReferenceApp::class.java, *args)
}