package ballgame.models.shapes

import ballgame.models.Ball
import ballgame.physics.distance
import ballgame.physics.shapeSize
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import kotlin.math.abs


class Square(px: Double = 0.0, py: Double = 0.0) : Rectangle(shapeSize, shapeSize), Draggable,
    Collisible {

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
            ball.centerX < layoutX -> layoutX
            ball.centerX > layoutX + shapeSize -> layoutX + shapeSize
            else -> ball.centerX
        }

        val closestPointY = when {
            ball.centerY < layoutY -> layoutY
            ball.centerY > layoutY + shapeSize -> layoutY + shapeSize
            else -> ball.centerY
        }

        return distance(
            ball.centerX + ball.vx,
            ball.centerY + ball.vy,
            closestPointX,
            closestPointY
        ) <= ball.radiusX
    }

    override fun getAfterCollideSpeed(ball: Ball): Pair<Double, Double> {
        val xContain = abs(ball.centerX + ball.vx - centerX) - (ball.radiusX + shapeSize / 2)
        val yContain = abs(ball.centerY + ball.vy - centerY) - (ball.radiusX + shapeSize / 2)

        if (xContain <= 0 && yContain <= 0 && xContain <= yContain) {
            return ball.vx to -ball.vy
        }
        if (xContain <= 0 && yContain <= 0 && xContain >= yContain) {
            return -ball.vx to ball.vy
        }
        return ball.vx to ball.vy
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