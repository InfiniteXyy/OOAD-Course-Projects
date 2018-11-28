package ballgame.models.shapes

import ballgame.models.Ball

interface Collisible {
    fun isCollide(ball: Ball): Boolean
    fun getAfterCollideSpeed(ball: Ball): Pair<Double, Double>
}