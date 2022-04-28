package com.example.lotus.src

class Card(private val cardName: String, private val cardGame: Int?, private val isTeamPlay: Boolean ) {
    val name: String = cardName
    val game: Int? = cardGame
    val isTeam: Boolean = isTeamPlay
}