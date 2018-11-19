package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.Circle
import ballgame.models.ShapeType
import ballgame.models.Square
import ballgame.models.Triangle
import javafx.scene.control.Button
import javafx.scene.shape.Shape
import tornadofx.*


class ToolboxView : View("Toolbox") {

    private val store: Store by inject()
    private val shapes = listOf<ShapeType>(Square(), Circle(), Triangle())
    private val toolButtons = shapes.map { ToolButton(it) }
    override val root = flowpane {
        addClass(Styles.toolbox)
        // add buttons
        toolButtons.forEach { add(it) }
    }

    init {
        // add onClick action for each button
        toolButtons.forEach {
            it.action {
                store.setEditShape(it.editShape)
            }
        }
    }
}

class ToolButton(private val myShape: ShapeType) : Button() {
    init {
        add(myShape as Shape)
    }

    val editShape: ShapeType
        get() {
            return when (myShape) {
                is Triangle -> Triangle()
                is Square -> Square()
                else -> Circle()
            }
        }
}
