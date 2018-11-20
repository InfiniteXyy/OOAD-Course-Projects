package ballgame.models

import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Ellipse

class Circle : Ellipse(9.0, 9.0), DraggableShape {
    override var isDragging: Boolean = false

    override val typeText = "circle"

    override fun followMouse(event: MouseEvent) {
        layoutX = event.x
        layoutY = event.y
    }

    init {
        fill = Color.SANDYBROWN
    }
}