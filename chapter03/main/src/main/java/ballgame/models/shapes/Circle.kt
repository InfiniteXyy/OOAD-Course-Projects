package ballgame.models.shapes

import ballgame.models.Ball
import ballgame.physics.*
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Ellipse

class Circle : Ellipse(shapeSize / 2, shapeSize / 2), Draggable, Collisible {
    override fun isCollide(ball: Ball): Boolean {
        if (distance(ball.centerX + ball.vx, ball.centerY + ball.vy, layoutX, layoutY) <= ball.radiusX + radiusX) {
            return true
        }
        return false
    }

    override fun getAfterCollideSpeed(ball: Ball): Pair<Double, Double> {
        val dx = ball.centerX - layoutX
        val dy = ball.centerY - layoutY
        return reflectBy(ball.vx to ball.vy, dx to dy)
    }


    companion object {
        val typeText = "circle"
    }

    override fun followMouse(event: MouseEvent) {
        layoutX = event.x
        layoutY = event.y
    }

    init {
        fill = Color.SANDYBROWN
    }
}