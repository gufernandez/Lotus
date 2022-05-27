package com.example.lotus.src

import com.example.lotus.R

class GameCards {
    private var gameCards = arrayOf(
        Card("Capitais", R.id.action_GameFragment_to_CapitalsFragment, true, true, 2, 10),
        Card("Telepatia", R.id.action_GameFragment_to_TelepathyFragment, true, true, 2, 10),
        Card("Jogral", R.id.action_GameFragment_to_JogralFragment, true, true, 2, 10),
        Card("Música", R.id.action_GameFragment_to_WordFragment, true, true, 2, 10),
        Card("Jokenpô Menos Um", null, true, true, 2, 10),
        Card("Cham Cham Cham", null, true, true,2, 10),
        Card("Sem Sentido", null, false, true,0,10),
        Card("Jogo do Pi", null, true, false,2, 0),
        Card("007 Bang", null, true, false,2,0),
        Card("Categoria", null, true, false,2,0)
    )

    private val duelCards = arrayOf("Roubar Estrela", "Dar Estrela", "Roubar Moedas", "Dar Moedas")

    fun getRandomGameCard(isCompetitive: Boolean): Card {
        var cards: Array<Card>
        if (isCompetitive) {
            cards = gameCards.filter { it.isCompetitive }.filter { it.competitiveQuantity > 0}.toTypedArray()

            if (cards.isEmpty()) {
                reshuffleCompCards()
                cards = gameCards.filter { it.isCompetitive }.filter { it.competitiveQuantity > 0}.toTypedArray()
            }
        } else {
            cards = gameCards.filter { it.isSolo }.filter { it.soloQuantity > 0 }.toTypedArray()

            if (cards.isEmpty()) {
                reshuffleSoloCards()
                cards = gameCards.filter { it.isSolo }.filter { it.soloQuantity > 0 }.toTypedArray()
            }
        }

        val cardN = cards.size -1
        val chosenCard = cards[getRandomIndex(cardN)]

        if (isCompetitive) {
            --chosenCard.competitiveQuantity
        } else {
            --chosenCard.soloQuantity
        }
        return chosenCard
    }

    fun getRandomDuelCard(): String {
        return when (getRandomIndex(99)) {
            in 0..4 -> duelCards[0]
            in 5..9 -> duelCards[0]
            in 10..54 -> duelCards[2]
            else -> duelCards[3]
        }
    }

    fun addGameCard(cardName: String, isCompetitive: Boolean, isSolo: Boolean) {
        val newGameCard = Card(cardName = cardName, cardGame = null, competitiveMode = isCompetitive, soloMode = isSolo, compQ = 2, soloQ = 10)
        val cardsList = gameCards.toMutableList()
        cardsList.add(newGameCard)
        gameCards = cardsList.toTypedArray()
    }

    private fun reshuffleCompCards() {
        gameCards.filter { it.isCompetitive }.map { it.competitiveQuantity = 2}
    }

    private fun reshuffleSoloCards() {
        gameCards.filter { it.isSolo }.map { it.soloQuantity = 10}
    }

    private fun getRandomIndex(maxN: Int): Int {
        var randIndex = 0

        for (i in 0..50) {
            randIndex = (0..maxN).random()
        }

        return randIndex
    }

}