package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import javafx.geometry.Pos
import tornadofx.*

class Footer : View() {
    private val store: Store by inject()
    // binding label
    private val labelText = stringBinding(store.gameRunning) {
        if (value) "Game running" else "Ready for game"
    }
    // binding button text
    private val buttonText = stringBinding(store.gameRunning) {
        if (value) "Pause" else "Start"
    }
    override val root = borderpane {
        addClass(Styles.footer)
        left {
            hbox {
                style { alignment = Pos.CENTER_LEFT }
                label(labelText)
            }
        }
        right {
            button(buttonText).action { store.toggleGameRunning() }
        }
    }
}