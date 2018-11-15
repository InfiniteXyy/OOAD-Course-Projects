package game

import java.util.*

open class ComputerPlayer : Player() {
    override var cards: MutableList<Card> = ArrayList()
        get() {
            val cards = super.cards
            return if (isDrawing) {
                cards.subList(0, minOf(cards.size, 2))
            } else {
                cards
            }
        }

    fun fillCards(deck: Deck) {
        while (this.cardSum <= 17) {
            this.addCard(deck.drawCard())
        }
    }
}
