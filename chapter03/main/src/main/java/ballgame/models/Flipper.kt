package ballgame.models

import ballgame.models.shapes.GizmoShape
import ballgame.physics.distance
import ballgame.physics.flipperHeight
import ballgame.physics.projPointOnLine
import ballgame.physics.reflectBy
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Rotate
import kotlin.math.PI
import kotlin.math.tan

class Flipper(val isLeft: Boolean = true) : Rectangle(80.0, flipperHeight), GizmoShape {
    private val speed = 4

    override fun getAfterCollideSpeed(ball: Ball): Pair<Double, Double> {
        return reflectBy(ball.vx to ball.vy, tan(rotate.angle / 180 * PI) to -1.0)
    }

    override fun isCollide(ball: Ball): Boolean {
        val lp = if (isLeft) {
            layoutX to layoutY
        } else {
            layoutX + width to layoutY
        }
        val (x1, y1) = projPointOnLine(ball.centerX + ball.vx to ball.centerY + ball.vy, lp,  rotate.angle)
        if (x1 <= lp.first && isLeft || x1 >= lp.first && !isLeft) {
            return false
        }
        if (distance(x1, y1, lp.first, lp.second) >= width) {
            return false
        }
        return distance(x1, y1, ball.centerX + ball.vx, ball.centerY + ball.vy) <= ball.radiusX
    }

    override fun followMouse(event: MouseEvent) {
        layoutX = if (isLeft) {
            event.x - height / 2
        } else {
            event.x - width + height / 2
        }
        layoutY = event.y - height / 2
    }

    private var isUp = false
    private val rotate = Rotate()

    fun updatePosition(elapsedSeconds: Long) {
        if (isLeft) {
            if (isUp) rotate.angle -= elapsedSeconds * speed
            else rotate.angle += elapsedSeconds * speed
            if (rotate.angle <= -90.0 && isUp || rotate.angle >= 0 && !isUp) isUp = !isUp
        } else {
            if (isUp) rotate.angle += elapsedSeconds * speed
            else rotate.angle -= elapsedSeconds * speed
            if (rotate.angle >= 90.0 && isUp || rotate.angle <= 0 && !isUp) isUp = !isUp
        }

    }

    init {
        transforms.add(rotate)
        if (isLeft) {
            rotate.pivotX = layoutX + height / 2
        } else {
            rotate.pivotX = layoutX + width - height / 2
        }
        rotate.pivotY = layoutY + height / 2
        arcHeight = 8.0
        arcWidth = 8.0
        fill = Color.ROSYBROWN
    }
}