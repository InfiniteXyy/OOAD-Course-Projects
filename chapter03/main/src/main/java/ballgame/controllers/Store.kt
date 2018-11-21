package ballgame.controllers

import ballgame.models.World
import ballgame.models.shapes.Circle
import ballgame.models.shapes.Draggable
import ballgame.models.shapes.Square
import ballgame.models.shapes.Triangle
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.shape.Shape
import tornadofx.*

class Store : Controller() {
    val map = World()
    val gameRunning = SimpleBooleanProperty(false)
    val mapEditing = SimpleBooleanProperty(false)
    var draggingShape: Draggable? = null

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
        draggingShape = when (shape) {
            is Circle -> Circle()
            is Triangle -> Triangle()
            is Square -> Square()
            else -> null
        }
    }
}

