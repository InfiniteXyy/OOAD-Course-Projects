package ballgame.physics

import kotlin.math.*

const val shapeSize = 20.0
const val flipperHeight = 8.0
fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
}

fun reflectBy(a: Pair<Double, Double>, b: Pair<Double, Double>): Pair<Double, Double> {
    val u = b.normalize()
    val au = a * u
    val d = u * au
    return a - d * 2.0
}

fun distancePointToLine(p: Pair<Double, Double>, lp: Pair<Double, Double>, length: Double, angle: Double): Double {
    val (x1, y1) = projPointOnLine(p, lp, angle)
    return distance(x1, y1, p.first, p.second)
}

fun projPointOnLine(p: Pair<Double, Double>, lp: Pair<Double, Double>, angle: Double): Pair<Double, Double> {
    val k = tan(angle / 180 * PI)
    val b = lp.second - k * lp.first
    val x0 = p.first
    val y0 = p.second
    val x1 = (k * (y0 - b) + x0) / (k * k + 1)
    val y1 = k * x1 + b
    return x1 to y1
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
    println(a * b)
}