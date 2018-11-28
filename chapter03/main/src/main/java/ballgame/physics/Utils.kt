package ballgame.physics

import kotlin.math.sqrt

const val shapeSize = 20.0
fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
}

fun reflectBy(a: Pair<Double, Double>, b: Pair<Double, Double>): Pair<Double, Double> {
    val u = b.normalize()
    val au = a * u
    val d = u * au
    return a - d * 2.0
}

fun Pair<Double, Double>.vLength() = sqrt(first * first + second * second)
fun Pair<Double, Double>.normalize() = first / vLength() to second / vLength()

operator fun Pair<Double, Double>.times(another: Pair<Double, Double>): Double {
    return first * another.first + second * another.second
}

operator fun Pair<Double, Double>.times(time: Double): Pair<Double, Double> {
    return first * time to second * time
}

operator fun Pair<Double, Double>.minus(another: Pair<Double, Double>): Pair<Double, Double> {
    return first - another.first to second - another.second
}

operator fun Pair<Double, Double>.plus(another: Pair<Double, Double>): Pair<Double, Double> {
    return first + another.first to second + another.second
}


fun main(args: Array<String>) {
    val a = 1.0 to 2.0
    val b = 3.0 to 4.0
    println(b.normalize() * (a * b.normalize()))

    val c = reflectBy(a, b)
    println(c)
}