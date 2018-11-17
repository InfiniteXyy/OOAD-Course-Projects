package ballgame.views

import javafx.scene.Parent
import javafx.scene.paint.Color
import tornadofx.*

class ToolsArea : View() {
    override val root = vbox {
        style {
            backgroundColor += Color.DEEPPINK
            fillHeight = true
        }
    }
}