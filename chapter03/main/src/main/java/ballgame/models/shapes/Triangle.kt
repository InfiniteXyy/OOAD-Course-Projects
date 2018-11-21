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
), Draggable, Collisible {
    override fun isCollide(ball: Ball): Boolean {
        if (distance(
                ball.centerX + ball.vx,
                ball.centerY + ball.vy,
                layoutX,
                layoutY
            ) <= ball.radiusX + shapeSize / 2
        ) {
            return true
        }
        return false
    }

    companion object {
        val typeText = "triangle"
    }

    override fun followMouse(event: MouseEvent) {
        layoutX = event.x
        layoutY = event.y
    }

    init {
        fill = Color.FORESTGREEN
    }
}