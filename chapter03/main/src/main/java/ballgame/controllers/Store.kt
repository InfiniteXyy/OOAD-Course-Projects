package ballgame.controllers

import ballgame.models.shapes.DraggableShape
import ballgame.models.World
import javafx.beans.property.SimpleBooleanProperty
import tornadofx.*

class Store : Controller() {
    val map = World()
    val gameRunning = SimpleBooleanProperty(false)
    val mapEditing = SimpleBooleanProperty(false)
    var draggingShape: DraggableShape? = null

    fun toggleGameRunning(running: Boolean = !gameRunning.value) = with(gameRunning) {
        set(running)
    }

    fun toggleMapEditing(editing: Boolean = !mapEditing.value) = with(mapEditing) {
        if (gameRunning.value) {
            toggleGameRunning(false)
        }
        set(editing)
    }

    fun setEditShape(draggableShape: DraggableShape?) {
        if (draggableShape != null)
            println(draggableShape)
        draggingShape = draggableShape
    }
}

