package ballgame.models.shapes

import ballgame.models.Ball

interface Collisible {
    fun isCollide(ball: Ball): Boolean
    fun getCollideData(ball: Ball): Pair<Double, Double>
}