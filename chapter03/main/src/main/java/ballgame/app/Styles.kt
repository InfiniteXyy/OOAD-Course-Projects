package ballgame.app

import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val footer by cssclass()
        val root by cssclass()
        val toolbox by cssclass()
        val playDesk by cssclass()
        val editDesk by cssclass()
    }

    init {
        footer {
            padding = box(10.px)
            alignment = Pos.CENTER
            spacing = 20.px
            star {
                spacing = 10.px
            }
            backgroundColor += Color.rgb(233, 233, 233)
        }
        root {
            prefWidth = 350.px
            prefHeight = 600.px
        }
        toolbox {
            prefWidth = 130.px
            prefHeight = 270.px
            padding = box(20.px, 10.px)
            vgap = 10.px
            hgap = 10.px
        }
        playDesk {
            backgroundColor += Color.WHITE
        }
        editDesk {
            backgroundColor += Color.DARKSLATEGRAY
        }
    }
}