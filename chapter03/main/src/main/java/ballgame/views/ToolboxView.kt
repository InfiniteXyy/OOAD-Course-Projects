package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.shapes.Circle
import ballgame.models.shapes.Square
import ballgame.models.shapes.Triangle
import javafx.scene.control.Button
import javafx.scene.shape.Shape
import tornadofx.*


class ToolboxView : View("Toolbox") {

    private val store: Store by inject()
    private val shapes = listOf<Shape>(Square(), Circle(), Triangle())
    private val toolButtons = shapes.map {
        Button().apply {
            add(it)
            action {
                store.setEditShape(it)
            }
        }
    }
    override val root = vbox {
        addClass(Styles.toolBox)
        label("Shapes")
        flowpane {
            addClass(Styles.toolPane)
            toolButtons.forEach { add(it) }
        }
        label("Controllers")

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

