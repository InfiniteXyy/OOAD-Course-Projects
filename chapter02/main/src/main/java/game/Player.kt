package game

import java.util.*

open class Player {
    val userId: Int = _ID++
    open val cards: MutableList<Card> = ArrayList()
    var isDrawing: Boolean = true
        private set
    var money: Int = 10
        private set
    var moneyOnDesk: Int = 0

    val cardSum: Int
        get() {
            return if (cards.count { it.value == 1 } == 0) {
                cards.sumBy { it.value }
            } else {
                val sum = cards.sumBy { it.value }
                when (sum) {
                    !in 0..21 -> sum
                    else -> sum + 10
                }
            }
        }

    fun addMoney(money: Int) {
        this.money += money
    }

    fun addCard(card: Card) {
        this.cards.add(card)
    }

    fun setStopDrawing() {
        isDrawing = false
    }

    fun reset() {
        this.cards.clear()
        this.isDrawing = true
    }

    open fun showCards(): String {
        val cardsInfo = cards.joinToString(separator = " ")
        return if (isDrawing) {
            cardsInfo
        } else {
            "$cardsInfo($cardSum)"
        }
    }

    companion object {
        private var _ID = 1
    }
}
