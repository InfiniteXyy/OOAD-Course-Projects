package ballgame.controllers

import javafx.beans.property.SimpleBooleanProperty
import tornadofx.*

class Store : Controller() {
    val gameRunning = SimpleBooleanProperty(true)

    fun toggleGameRunning(running: Boolean) = with(gameRunning) {
        set(running)
    }

}