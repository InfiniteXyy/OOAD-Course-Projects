package ballgame.models

import ballgame.models.shapes.Collisible
import ballgame.models.shapes.Draggable
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Rotate

class Flipper(val isLeft: Boolean = true) : Rectangle(80.0, 12.0), Draggable, Collisible {
    override fun isCollide(ball: Ball): Boolean {
        return false
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
            if (isUp) rotate.angle -= elapsedSeconds * 2
            else rotate.angle += elapsedSeconds * 2
            if (rotate.angle <= -90.0 && isUp || rotate.angle >= 0 && !isUp) isUp = !isUp
        } else {
            if (isUp) rotate.angle += elapsedSeconds * 2
            else rotate.angle -= elapsedSeconds * 2
            if (rotate.angle >= 90.0 && isUp || rotate.angle <= 0 && !isUp) isUp = !isUp
        }

    }

    init {
        transforms.add(rotate)
        if (isLeft) {
            rotate.pivotX = layoutX + 6
        } else {
            rotate.pivotX = layoutX + width - 6
        }
        rotate.pivotY = layoutY + 6
        arcHeight = 8.0
        arcWidth = 8.0
        fill = Color.ROSYBROWN
    }
}