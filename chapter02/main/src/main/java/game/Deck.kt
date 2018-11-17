package game

import java.util.*

internal class Deck {
    private var cards: MutableList<Card> = ArrayList()

    init {
        // init with cards
        for (i in 1..13) {
            for (j in 0..3) {
                this.cards.add(Card(j, i))
            }
        }
        cards.shuffle()
    }

    fun drawCard(): Card {
        return this.cards.removeAt(0)
    }
}
