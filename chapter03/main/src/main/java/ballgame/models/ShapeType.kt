package ballgame.models

import javafx.scene.input.MouseEvent


interface ShapeType {
    val typeText: String
    fun followMouse(event: MouseEvent)
}