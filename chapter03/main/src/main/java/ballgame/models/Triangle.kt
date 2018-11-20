package ballgame.models

import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class Triangle : Polygon(
    0.0, 0.0,
    18.0, 0.0,
    0.0, 18.0
), DraggableShape {

    override var isDragging: Boolean = true

    override val typeText = "circle"

    override fun followMouse(event: MouseEvent) {
        layoutX = event.x
        layoutY = event.y
    }

    init {
        fill = Color.FORESTGREEN
    }
}