package ballgame

import ballgame.views.PlayArea
import ballgame.views.ToolsArea
import javafx.scene.Parent
import tornadofx.*

class GameDesk : View() {
    private val playArea = PlayArea()
    private val toolsArea = ToolsArea()
    override val root = hbox {
        pane {
            add(playArea)
            add(toolsArea)
        }
    }
}