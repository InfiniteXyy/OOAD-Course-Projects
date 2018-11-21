package ballgame.models

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Rotate

class Flipper : Rectangle(80.0, 400.0, 80.0, 12.0) {
    private var isUp = false
    var active: Boolean = false
    private val rotate = Rotate()
    fun updatePosition(elapsedSeconds: Long) {
        if (isUp) rotate.angle += elapsedSeconds * 2
        else rotate.angle -= elapsedSeconds * 2
        if (rotate.angle >= 90.0 && isUp || rotate.angle <= 0 && !isUp) isUp = !isUp
    }

    init {
        transforms.add(rotate)
        rotate.pivotX = 86.0
        rotate.pivotY = 406.0
        arcHeight = 8.0
        arcWidth = 8.0
        fill = Color.ROSYBROWN
    }
}