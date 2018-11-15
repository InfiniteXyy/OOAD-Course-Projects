package game

interface GameInterface {

    val computerPlayer: Player

    val winner: Player

    val deltaMoney: IntArray

    fun drawCardForPlayer(player: Player)
}
