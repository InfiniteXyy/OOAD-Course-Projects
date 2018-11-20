package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.shapes.Circle
import ballgame.models.shapes.DraggableShape
import ballgame.models.shapes.Square
import ballgame.models.shapes.Triangle
import javafx.scene.control.Button
import javafx.scene.shape.Shape
import tornadofx.*


class ToolboxView : View("Toolbox") {

    private val store: Store by inject()
    private val shapes = listOf<Shape>(Square(), Circle(), Triangle())
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
                store.setEditShape(it.editDraggableShape)
            }
        }
    }
}

class ToolButton(private val myShape: Shape) : Button() {
    init {
        add(myShape)
    }

    val editDraggableShape: DraggableShape
        get() {
            return when (myShape) {
                is Triangle -> Triangle()
                is Square -> Square()
                else -> Circle()
            }

        }
}
