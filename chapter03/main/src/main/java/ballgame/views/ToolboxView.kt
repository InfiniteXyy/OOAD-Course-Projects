package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.Circle
import ballgame.models.DraggableShape
import ballgame.models.Square
import ballgame.models.Triangle
import javafx.scene.control.Button
import javafx.scene.shape.Shape
import tornadofx.*


class ToolboxView : View("Toolbox") {

    private val store: Store by inject()
    private val shapes = listOf<DraggableShape>(Square(), Circle(), Triangle())
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

class ToolButton(private val myDraggableShape: DraggableShape) : Button() {
    init {
        add(myDraggableShape as Shape)
    }

    val editDraggableShape: DraggableShape
        get() {
            return when (myDraggableShape) {
                is Triangle -> Triangle()
                is Square -> Square()
                else -> Circle()
            }
        }
}
