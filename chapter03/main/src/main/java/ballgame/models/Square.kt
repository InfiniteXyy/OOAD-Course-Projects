package ballgame.models

import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class Square : Rectangle(18.0, 18.0), ShapeType {
    override val typeText = "square"

    override fun followMouse(event: MouseEvent) {
        layoutX = event.x - 9.0
        layoutY = event.y - 9.0
    }

    init {
        fill = Color.SKYBLUE
    }

}