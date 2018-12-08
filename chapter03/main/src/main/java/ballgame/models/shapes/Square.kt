package ballgame.models.shapes

import ballgame.models.Ball
import ballgame.physics.distance
import ballgame.physics.reflectBy
import ballgame.physics.shapeSize
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class Square(px: Double = 0.0, py: Double = 0.0) : Rectangle(shapeSize, shapeSize), GizmoShape {
    private var collideType = 0

    var centerX: Double
        get() = layoutX + shapeSize / 2
        set(value) {
            layoutX = value - shapeSize / 2
        }
    var centerY: Double
        get() = layoutY + shapeSize / 2
        set(value) {
            layoutY = value - shapeSize / 2
        }

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

        collideType = when {
            closestPointX == layoutX || closestPointX == layoutX + shapeSize -> 0
            closestPointY == layoutY || closestPointY == layoutY + shapeSize -> 1
            else -> 0
        }
        if (collideType == 0 && closestPointY == layoutY || closestPointY == layoutY + shapeSize) {
            collideType = 2
        }

        return distance(
            ball.centerX + ball.vx,
            ball.centerY + ball.vy,
            closestPointX,
            closestPointY
        ) <= ball.radiusX
    }

    override fun getAfterCollideSpeed(ball: Ball): Pair<Double, Double> {
        return when (collideType) {
            0 -> -ball.vx to ball.vy
            1 -> ball.vx to -ball.vy
            else -> {
                val dx = ball.centerX - centerX
                val dy = ball.centerY - centerY
                return reflectBy(ball.vx to ball.vy, dx to dy)
            }
        }
    }

    override fun followMouse(event: MouseEvent) {
        centerX = event.x
        centerY = event.y
    }

    init {
        layoutX = px
        layoutY = py
        fill = Color.SKYBLUE
    }

}