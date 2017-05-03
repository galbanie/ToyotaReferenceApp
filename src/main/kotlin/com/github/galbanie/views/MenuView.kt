package com.github.galbanie.views

import com.github.galbanie.ToyotaReferenceScope
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.scene.control.Alert
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*
import kotlin.system.exitProcess

/**
 * Created by Galbanie on 2017-05-02.
 */
class MenuView : View() {

    override val scope = super.scope as ToyotaReferenceScope

    override val root = menubar {
        menu("Toyota Reference App"){
            menuitem("Update"){

            }.graphic = FontAwesomeIconView(FontAwesomeIcon.DOWNLOAD)
            menuitem("preferences","Shortcut+,", FontAwesomeIconView(FontAwesomeIcon.COGS)){
                find(PreferencesView::class).openModal(stageStyle = StageStyle.DECORATED)
            }.isDisable = true
            menuitem("Version"){
                alert(Alert.AlertType.INFORMATION,header = "Version", content =
                        "Application : ${scope.versions["app"]}\n" +
                        "Data Source : ${scope.versions["data"]}\n" +
                        "Resources : ${scope.versions["res"]}"
                )
            }.graphic = FontAwesomeIconView(FontAwesomeIcon.CODE_FORK)
            separator()
            menuitem("Quit","Shortcut+Q", FontAwesomeIconView(FontAwesomeIcon.POWER_OFF)){
                exitProcess(0)
            }
        }
        menu("Help"){
            menuitem("About", graphic = FontAwesomeIconView(FontAwesomeIcon.QUESTION)){
                find(AboutView::class).openModal()
            }.isDisable = true
            menuitem("Report"){
                find(ReportView::class).openModal()
            }.graphic = FontAwesomeIconView(FontAwesomeIcon.BUG)

        }
    }
}
