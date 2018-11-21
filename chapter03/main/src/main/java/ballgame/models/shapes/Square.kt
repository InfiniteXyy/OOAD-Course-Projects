package ballgame.models.shapes

import ballgame.models.Ball
import ballgame.physics.distance
import ballgame.physics.shapeSize
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


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
        if (distance(
                ball.centerX + ball.vx,
                ball.centerY + ball.vy,
                centerX,
                centerY
            ) <= ball.radiusX + shapeSize / 2
        ) {
            return true
        }
        return false
    }

    companion object {
        val typeText = "square"
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