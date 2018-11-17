package ballgame

import javafx.scene.control.Label
import javafx.scene.paint.Color
import tornadofx.*

class StatusBar : View() {
    override val root = hbox {
        style {
            fillWidth = true
            backgroundColor += Color.rgb(233, 233, 233)
        }
        pane { add(Label("准备就绪")) }
    }
}