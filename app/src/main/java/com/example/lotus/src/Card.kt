package com.example.lotus.src

class Card(private val cardName: String, private val cardGame: Int?,
           private val competitiveMode: Boolean, private val soloMode: Boolean,
           private val compQ: Int, private val soloQ: Int) {
    val name: String = cardName
    val game: Int? = cardGame
    val isCompetitive: Boolean = competitiveMode
    val isSolo: Boolean = soloMode
    var competitiveQuantity: Int = compQ
    var soloQuantity: Int = soloQ
}