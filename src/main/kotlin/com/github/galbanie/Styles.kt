package com.github.galbanie

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val welcomeScreen by cssclass()
        val controlMenu by cssclass()
        val content by cssclass()
        val heading by cssclass()
        val title by cssid()
        val h1 by cssid()
        val copyright by cssid()
        val header by cssclass()
        val footer by cssclass()
    }

    init {
        welcomeScreen {
            padding = box(10.px)
            backgroundColor += LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, Stop(0.0, c("#028aff")), Stop(1.0, c("#003780")))
            heading {
                fontSize = 3.em
                textFill = Color.WHITE
                fontWeight = FontWeight.BOLD
            }
            content {
                padding = box(25.px)
                button {
                    fontSize = 22.px
                }
            }
        }

        controlMenu{
            padding = box(10.px)
            button{
                backgroundColor += Color.TRANSPARENT
                textAlignment = TextAlignment.LEFT

                and(hover){
                    backgroundColor += Color.LIGHTSLATEGRAY
                    textFill = Color.WHITE
                }
            }
        }

        title {
            fontSize = 2.em
            fontWeight = FontWeight.EXTRA_BOLD
            textFill = c(0, 56, 101, 0.42)
        }

        h1{
            fontSize = 2.em
            fontWeight = FontWeight.BOLD
            textFill = c(0, 56, 101, 1.00)
        }

        copyright {
            fontSize = 1.5.em
            fontWeight = FontWeight.EXTRA_BOLD
            textFill = c(0, 56, 101, 0.42)
        }

        header {
            alignment = Pos.CENTER
            star {
                alignment = Pos.CENTER_LEFT
            }
        }

        footer {
            padding = box(10.px)
            alignment = Pos.CENTER
            spacing = 20.px
            star {
                spacing = 10.px
            }
        }
    }
}