package ballgame.models.shapes

import ballgame.models.Ball
import ballgame.physics.distance
import ballgame.physics.shapeSize
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class Triangle : Polygon(
    0.0, 0.0,
    shapeSize, 0.0,
    0.0, shapeSize
), DraggableShape, CollisibleShape {
    override fun isCollide(ball: Ball): Boolean {
        if (distance(ball.centerX, ball.centerY, layoutX, layoutY) <= ball.radiusX + shapeSize / 2) {
            return true
        }
        return false
    }

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