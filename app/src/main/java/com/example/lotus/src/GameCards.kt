package com.example.lotus.src

import com.example.lotus.R

class GameCards {
    private val gameCards = arrayOf(
        Card("Capitais", R.id.action_GameFragment_to_CapitalsFragment, false),
        Card("Telepatia Time", R.id.action_GameFragment_to_TelepathyFragment, true),
        Card("Telepatia Versus", R.id.action_GameFragment_to_TelepathyFragment, false),
        Card("Jogral", R.id.action_GameFragment_to_JogralFragment, false),
        Card("Música", R.id.action_GameFragment_to_WordFragment, false),
        Card("Música Versus", R.id.action_GameFragment_to_WordFragment, false),
        Card("Jokenpô Menos Um", null, false),
        Card("Cham Cham Cham", null, false),
        Card("Jogo do Pi", null, false)
    )

    private val duelCards = arrayOf(
        Card("Roubar Estrela", null, false),
        Card("Dar Estrela", null, false),
        Card("Roubar Moedas", null, false),
        Card("Dar Moedas", null, false)
    )

    fun getRandomGameCard(isTeam: Boolean): Card {
        var cards = gameCards

        if (!isTeam) {
            cards = cards.filter { !it.isTeam }.toTypedArray()
        }

        val cardN = cards.size -1

        return cards[getRandomIndex(cardN)]
    }

    fun getRandomDuelCard(): Card {
        return when (getRandomIndex(99)) {
            in 0..4 -> duelCards[0]
            in 5..9 -> duelCards[0]
            in 10..54 -> duelCards[2]
            else -> duelCards[3]
        }
    }

    private fun getRandomIndex(maxN: Int): Int {
        var randIndex = 0

        for (i in 0..50) {
            randIndex = (0..maxN).random()
        }

        return randIndex
    }

}