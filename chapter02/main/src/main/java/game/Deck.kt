package game

import java.util.*

class Deck {
    private var cards: MutableList<Card> = ArrayList()

    init {
        // init with cards
        for (i in 1..13) {
            for (j in 0..3) {
                val card = Card(j, i)
                this.cards.add(card)
            }
        }
        cards.shuffle()
    }

    fun drawCard(): Card {
        return this.cards.removeAt(0)
    }
}
