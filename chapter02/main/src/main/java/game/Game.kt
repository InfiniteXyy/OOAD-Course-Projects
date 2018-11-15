package game

class Game private constructor() : GameInterface {

    private var players: List<Player>? = null
    private var deck: Deck = Deck()
    override val computerPlayer: ComputerPlayer = ComputerPlayer()
    private var winnerId: Int = 0

    override//-1代表电脑玩家
    val winner: Player
        get() {
            var maxvalue = computerPlayer.cardSum
            computerPlayer.setStopDrawing()
            var winner = -1
            if (maxvalue > 21) {
                for (player in players!!) {
                    if (player.cardSum <= 21) {
                        maxvalue = player.cardSum
                        winner = player.userId
                        break
                    }
                }
            }
            for (player in players!!) {
                if (player.cardSum in (maxvalue + 1)..21) {
                    maxvalue = player.cardSum
                    winner = player.userId
                }
            }
            winnerId = winner
            for (player in players!!) {
                if (player.userId == winnerId) {
                    return player
                }
            }
            return computerPlayer
        }

    //TODO: 根据胜负情况与玩家投入的钱（money on desk）输出 delta 金额数组
    override val deltaMoney: IntArray
        get() {
            val delta = IntArray(players!!.size)

            var change = 0
            for ((i, player) in players!!.withIndex()) {
                change = if (player.userId == winnerId) {
                    player.moneyOnDesk
                } else {
                    0 - player.moneyOnDesk
                }
                delta[i] = change
            }
            return delta
        }

    init {
        computerPlayer.fillCards(deck)
    }

    override fun drawCardForPlayer(player: Player) {
        player.addCard(deck.drawCard())
    }

    companion object {
        fun createGame(players: List<Player>): Game {
            val game = Game()
            game.players = players
            return game
        }
    }
}
