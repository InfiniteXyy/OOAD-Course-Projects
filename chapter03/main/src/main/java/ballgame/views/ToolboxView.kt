package ballgame.views

import ballgame.app.Styles
import tornadofx.*

class ToolboxView : View("Toolbox") {
    override val root = borderpane {
        addClass(Styles.toolbox)
        center {
            label("tools to be added here")
        }
    }
}
