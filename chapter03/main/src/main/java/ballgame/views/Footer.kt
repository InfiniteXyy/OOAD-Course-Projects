package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import javafx.geometry.Pos
import tornadofx.*

class Footer : View() {
    private val store: Store by inject()

    override val root = borderpane {
        addClass(Styles.footer)
        left {
            hbox {
                style { alignment = Pos.CENTER_LEFT }
                checkbox("show speed") {
                    bind(store.needSpeedLine)
                }
            }
        }
        right {
            hbox {
                button {
                    text = "Reset"
                    action { store.world.reset() }
                    removeWhen { booleanBinding(store.gameRunning) { value } }
                }
                button {
                    text { bind(stringBinding(store.gameRunning) { if (value) "Pause" else "Start" }) }
                    action { store.toggleGameRunning() }
                    removeWhen { booleanBinding(store.mapEditing) { value } }
                }
                button {
                    text = "Save"
                    action { store.toggleMapEditing() }
                    removeWhen { booleanBinding(store.mapEditing) { value.not() } }
                }
            }
        }
    }
}