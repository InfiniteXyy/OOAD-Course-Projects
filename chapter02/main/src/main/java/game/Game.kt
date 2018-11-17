package game

class Game private constructor(players: List<Player>) : GameInterface {

    private val players: List<Player>
    private var deck: Deck = Deck()
    override val computerPlayer: ComputerPlayer = ComputerPlayer()
    private var winnerId: Int = 0

    override val winner: Player
        get() {
            var maxvalue = computerPlayer.cardSum
            computerPlayer.setStopDrawing()
            var winner = -1
            if (maxvalue > 21) {
                for (player in players) {
                    if (player.cardSum <= 21) {
                        maxvalue = player.cardSum
                        winner = player.userId
                        break
                    }
                }
            }
            for (player in players) {
                if (player.cardSum in (maxvalue + 1)..21) {
                    maxvalue = player.cardSum
                    winner = player.userId
                }
            }
            winnerId = winner
            for (player in players) {
                if (player.userId == winnerId) {
                    return player
                }
            }
            return computerPlayer
        }

    override val deltaMoney: IntArray
        get() {
            val delta = IntArray(players.size)

            var change: Int
            for ((i, player) in players.withIndex()) {
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
        this.players = players
    }

    override fun drawCardForPlayer(player: Player) {
        player.addCard(deck.drawCard())
    }

    companion object {
        fun createGame(players: List<Player>): Game {
            return Game(players)
        }
    }
}
