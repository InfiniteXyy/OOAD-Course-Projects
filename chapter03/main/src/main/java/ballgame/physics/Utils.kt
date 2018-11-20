package ballgame.physics

import kotlin.math.sqrt

const val shapeSize = 20.0
fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
}


fun main(args: Array<String>) {
    println(distance(0.0, 0.1, 0.3, 0.5))
}