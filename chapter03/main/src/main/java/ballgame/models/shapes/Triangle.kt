package ballgame.models.shapes

import ballgame.models.Ball
import ballgame.physics.distance
import ballgame.physics.isCircleIntersectLineSeg
import ballgame.physics.shapeSize
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon
import kotlin.math.abs

class Triangle : Polygon(
        0.0, 0.0,
        shapeSize, 0.0,
        0.0, shapeSize
), GizmoShape {
    override fun isCollide(ball: Ball): Boolean {
        val (x, y) = ball.vx + ball.centerX to ball.vy + ball.centerY
        val (x1, y1) = layoutX to layoutY
        val (x2, y2) = layoutX + shapeSize to layoutY
        val (x3, y3) = layoutX to layoutY + shapeSize
        if (isCircleIntersectLineSeg(x, y, ball.radiusX, x1, y1, x2, y2)) return true
        if (isCircleIntersectLineSeg(x, y, ball.radiusX, x2, y2, x3, y3)) return true
        if (isCircleIntersectLineSeg(x, y, ball.radiusX, x1, y1, x3, y3)) return true
        return false
    }

    override fun getAfterCollideSpeed(ball: Ball): Pair<Double, Double> {
        val bxnow = ball.centerX
        val bynow = ball.centerY
        if ((bynow + ball.radiusY < layoutY) && (bynow + ball.vy + ball.radiusX >= layoutY) && (bxnow + ball.vx + ball.radiusX >= layoutX) && (bxnow + ball.vx - ball.radiusX <= layoutX + shapeSize))
            return ball.vx to -ball.vy
        if ((bxnow + ball.radiusX < layoutX) && (bxnow + ball.vx + ball.radiusX >= layoutX) && (bynow + ball.vy + ball.radiusY >= layoutY) && (bynow + ball.vy - ball.radiusY <= layoutY + shapeSize))
            return -ball.vx to ball.vy
        return -ball.vy to -ball.vx
    }

    override fun followMouse(event: MouseEvent) {
        layoutX = event.x
        layoutY = event.y
    }

    init {
        fill = Color.FORESTGREEN
    }
}