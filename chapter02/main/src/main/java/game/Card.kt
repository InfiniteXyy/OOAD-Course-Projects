package game

class Card(private var type: Int, var value: Int) {

    val resourceName: String
        get() = getSuit(false) + resouseId[value - 1]

    init {
        assert(value in 1..13)
    }

    fun getSuit(needEmoji: Boolean): String {
        return if (needEmoji) suitEmoji[type] else suit[type]
    }

    fun setType(type: Int) {
        this.type = type
    }

    override fun toString(): String {
        return getSuit(true) + valueID[value - 1]
    }

    companion object {
        private val SPADE = 0
        private val HEART = 1
        private val DIAMOND = 2
        private val CLUB = 3
        private val suitEmoji = arrayOf("♠", "♥", "♦", "♣")
        private val suit = arrayOf("S", "H", "D", "C")
        private val resouseId =
            arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K")
        private val valueID =
            arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    }
}
