package ballgame.models.shapes

import ballgame.models.Ball

interface CollisibleShape {
    fun isCollide(ball: Ball): Boolean
}