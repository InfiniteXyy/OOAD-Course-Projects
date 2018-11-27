package ballgame.controllers

import ballgame.models.World
import ballgame.models.shapes.Draggable
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.shape.Shape
import tornadofx.*

class Store : Controller() {
    val world = World()
    val gameRunning = SimpleBooleanProperty(false)
    val mapEditing = SimpleBooleanProperty(false)
    var draggingShape: Draggable? = null
    var needSpeedLine = SimpleBooleanProperty(false)
    var leftFlipperKey = SimpleStringProperty("Q")
    var rightFlipperKey = SimpleStringProperty("E")

    fun toggleGameRunning(running: Boolean = !gameRunning.value) = with(gameRunning) {
        set(running)
    }

    fun toggleMapEditing(editing: Boolean = !mapEditing.value) = with(mapEditing) {
        if (gameRunning.value) {
            toggleGameRunning(false)
        }
        set(editing)
    }

    fun setEditShape(shape: Shape?) {
        if (shape != null) {
            println(shape)
            primaryStage.requestFocus()
        }
        draggingShape = shape as Draggable?
    }
}

