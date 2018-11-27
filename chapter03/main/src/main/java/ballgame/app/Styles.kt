package ballgame.app

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val footer by cssclass()
        val root by cssclass()
        val toolBox by cssclass()
        val toolPane by cssclass()
        val playDesk by cssclass()
        val settingDialog by cssclass()
        val title by cssid()
        val wrongTip by cssid()
    }

    init {
        footer {
            padding = box(10.px)
            alignment = Pos.CENTER
            spacing = 20.px

            checkBox {
                fontSize = 12.px
                textFill = Color.web("#4a4a4a")
            }
            star {
                spacing = 10.px
            }
            backgroundColor += Color.rgb(233, 233, 233)
        }
        root {
            prefWidth = 360.px
            prefHeight = 598.px
        }
        toolBox {
            prefWidth = 120.px
            prefHeight = 260.px
            padding = box(0.px, 10.px)
            label {
                textFill = Color.rgb(74, 74, 74)
                padding = box(10.px, 0.px)
            }
        }
        toolPane {
            vgap = 10.px
            hgap = 10.px
        }
        playDesk {
            backgroundColor += Color.WHITE
        }

        settingDialog {
            padding = box(10.0.px)
            prefHeight = 200.px
            prefWidth = 220.px
            title {
                fontSize = 2.0.em
                fontWeight = FontWeight.BOLD
                textFill = Color.rgb(74, 74, 74)
                padding = box(0.px, 0.px, 20.px, 0.px)
            }
            label {
                fontSize = 16.px
                padding = box(0.px, 10.px, 0.px, 0.px)
            }
            textField {
                prefWidth = 150.px
            }
            wrongTip {
                textFill = Color.INDIANRED
                padding = box(0.0.px, 0.0.px, 0.0.px, 10.0.px)
            }
        }
    }
}