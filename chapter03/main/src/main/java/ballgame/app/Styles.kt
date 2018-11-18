package ballgame.app

import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val footer by cssclass()
        val root by cssclass()
    }

    init {
        footer {
            padding = box(10.px)
            alignment = Pos.CENTER
            spacing = 20.px
            star {
                spacing = 10.px
            }
            backgroundColor += Color.rgb(233, 233, 233);
        }
        root {
            prefWidth = 350.px
            prefHeight = 600.px
        }
    }
}