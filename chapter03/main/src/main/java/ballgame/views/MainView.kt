package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import tornadofx.*

class MainView : View("Play the ball") {
    private val store: Store by inject()
    override val root = borderpane {
        addClass(Styles.root)
        top {
            menubar {
                menu("Game") {
                    item("New...")
                    item("Open...")
                    item("Save")
                }
                menu("Map") {
                    item("Edit map").action {
                        store.toggleMapEditing(true)
                        ToolboxView().openWindow()
                    }
                    item("Import map")
                    item("Map Setting")
                }
                menu("Help") {
                    item("About")
                    item("Check for updates...")
                }
            }
        }
        center(PlayArea::class)
        bottom(Footer::class)
    }
}