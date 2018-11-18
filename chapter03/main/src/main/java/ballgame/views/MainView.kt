package ballgame.views

import ballgame.app.Styles
import tornadofx.*

class MainView : View("Play the ball") {
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
                    item("Edit new map")
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