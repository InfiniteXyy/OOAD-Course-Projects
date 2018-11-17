package game

import kotlin.math.min

class ComputerPlayer : Player() {
    internal fun fillCards(deck: Deck) {
        while (super.cardSum <= 17) {
            this.addCard(deck.drawCard())
        }
    }

    override fun showCards(): String {
        val showCards = if (isDrawing) {
            cards.subList(0, min(cards.size, 2))
        } else {
            cards
        }
        val cardsInfo = showCards.joinToString(separator = " ")
        return if (isDrawing) {
            cardsInfo
        } else {
            "$cardsInfo($cardSum)"
        }
    }
}
