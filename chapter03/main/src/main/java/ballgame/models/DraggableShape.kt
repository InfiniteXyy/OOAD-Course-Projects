package ballgame.models

import javafx.scene.input.MouseEvent


interface DraggableShape {
    val typeText: String
    var isDragging: Boolean
    fun followMouse(event: MouseEvent)
}