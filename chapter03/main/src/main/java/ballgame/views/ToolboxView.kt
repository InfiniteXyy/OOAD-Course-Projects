package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.Flipper
import ballgame.models.shapes.Circle
import ballgame.models.shapes.Square
import ballgame.models.shapes.Triangle
import tornadofx.*


class ToolboxView : View("Toolbox") {

    private val store: Store by inject()
    override val root = vbox {
        addClass(Styles.toolBox)
        label("Shapes")
        flowpane {
            addClass(Styles.toolPane)
            button {
                add(Triangle())
                action { store.setEditShape(Triangle()) }
            }
            button {
                add(Square())
                action { store.setEditShape(Square()) }
            }
            button {
                add(Circle())
                action { store.setEditShape(Circle()) }
            }
        }
        label("Controllers")
        flowpane {
            addClass(Styles.toolPane)
            button("left") {
                action { store.setEditShape(Flipper(true)) }
            }
            button("right") {
                action { store.setEditShape(Flipper(false)) }
            }
        }

        label("Other settings")
        vbox {
            button("ball") {
                action {
                    tornadofx.find(MainView::class).apply {
                        toggleSettingBallWindow()
                    }
                }
            }
        }
    }
}

