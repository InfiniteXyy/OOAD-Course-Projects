package ballgame.models

class MapConfig {
    private val ballPosition = 10.0 to 10.0
    val ball: Ball by lazy {
        Ball(4.0, 1.0, ballPosition.first, ballPosition.second)
    }
}