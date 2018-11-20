package ballgame.models.shapes

import javafx.scene.input.MouseEvent


interface DraggableShape {
    val typeText: String
    var isDragging: Boolean
    fun followMouse(event: MouseEvent)
}