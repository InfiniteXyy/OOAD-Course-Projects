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
), GizmoShape {
    override fun isCollide(ball: Ball): Boolean {
        val closestPointX = when {
            ball.centerX + ball.vx < layoutX -> layoutX
            ball.centerX + ball.vx > layoutX + shapeSize -> layoutX + shapeSize
            else -> ball.centerX + ball.vx
        }

        val closestPointY = when {
            ball.centerY + ball.vy < layoutY -> layoutY
            ball.centerY + ball.vy > layoutY + shapeSize -> layoutY + shapeSize
            else -> ball.centerY + ball.vy
        }
        return distance(
                ball.centerX + ball.vx,
                ball.centerY + ball.vy,
                closestPointX,
                closestPointY
        ) <= ball.radiusX
    }
    override fun getAfterCollideSpeed(ball: Ball): Pair<Double, Double>
    {
        return ball.vx to ball.vy
    }

    override fun followMouse(event: MouseEvent) {
        layoutX = event.x
        layoutY = event.y
    }

    init {
        fill = Color.FORESTGREEN
    }
}