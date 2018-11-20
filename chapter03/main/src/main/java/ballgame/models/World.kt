package ballgame.models

import ballgame.models.shapes.Square
import javafx.scene.shape.Shape

class World {
    private val ballPosition = 10.0 to 10.0
    val ball: Ball by lazy {
        Ball(4.0, 1.0, ballPosition.first, ballPosition.second)
    }
    var shapes: MutableList<Shape> = ArrayList()

    init {
        shapes.add(Square(60.0, 80.0))
        shapes.add(Square(40.0, 30.0))
        shapes.add(Square(80.0, 125.0))
    }
}