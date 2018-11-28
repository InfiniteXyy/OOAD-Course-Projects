package ballgame.models

import ballgame.models.shapes.Circle
import ballgame.models.shapes.Square
import ballgame.models.shapes.Triangle
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.scene.shape.Shape


class World {
    val ball = Ball(10.0, 10.0)
    val shapes = SimpleListProperty<Shape>(FXCollections.observableArrayList())

    fun reset(all: Boolean = false) {
        if (all) shapes.clear()
        ball.vx = 4.0
        ball.vy = 1.0
        ball.onGround = false
        ball.centerX = 10.0
        ball.centerY = 10.0
        if (ball !in shapes)
            shapes.add(ball)
    }

    init {
        reset()
    }

    val shapeConfig: List<List<Any>> by lazy {
        shapes.map {
            listOf(
                when (it) {
                    is Triangle -> "triangle"
                    is Circle -> "circle"
                    is Square -> "square"
                    is Flipper -> if (it.isLeft) "flipper-l" else "flipper-r"
                    else -> "ball"
                }, it.layoutX, it.layoutY
            )
        }
    }

    fun loadShapes(data: List<List<String>>) {
        shapes.clear()
        reset()
        data.forEach { shape ->
            val newShape: Shape? = when (shape[0]) {
                "triangle" -> Triangle()
                "circle" -> Circle()
                "square" -> Square()
                "flipper-l" -> Flipper(true)
                "flipper-r" -> Flipper(false)
                else -> null
            }
            if (newShape != null) {
                newShape.layoutX = shape[1].toDouble()
                newShape.layoutY = shape[2].toDouble()
                shapes.add(newShape)
            }
        }

    }
}
