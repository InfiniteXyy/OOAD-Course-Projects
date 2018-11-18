package ballgame.controllers

import javafx.beans.property.SimpleBooleanProperty
import tornadofx.*

class Store : Controller() {
    val gameRunning = SimpleBooleanProperty(false)
    val mapEditing = SimpleBooleanProperty(false)

    fun toggleGameRunning(running: Boolean = !gameRunning.value) = with(gameRunning) {
        set(running)
    }
    fun toggleMapEditing(editing: Boolean) = with(mapEditing) {
        if (gameRunning.value) {
            toggleGameRunning(false)
        }
        set(editing)
    }
}