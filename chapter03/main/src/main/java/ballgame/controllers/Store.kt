package ballgame.controllers

import ballgame.models.ShapeType
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

class Store : Controller() {
    val gameRunning = SimpleBooleanProperty(false)
    val mapEditing = SimpleBooleanProperty(false)
    var editShapeType = SimpleObjectProperty<ShapeType>(null)

    fun toggleGameRunning(running: Boolean = !gameRunning.value) = with(gameRunning) {
        set(running)
    }

    fun toggleMapEditing(editing: Boolean = !mapEditing.value) = with(mapEditing) {
        if (gameRunning.value) {
            toggleGameRunning(false)
        }
        set(editing)
    }

    fun setEditShape(shape: ShapeType?) = with(editShapeType) {
        set(shape)
        println("toggle $value")
    }
}

