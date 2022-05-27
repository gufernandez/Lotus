package com.example.lotus

import android.content.res.Resources
import androidx.annotation.RawRes
import java.util.*

class Helper {
    val animationTime = 300

    fun getRandomCountryList(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.paises)

    fun getRandomCountry(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.paises).split(',')[0]

    fun getRandomFruit(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.frutas)

    fun getRandomColor(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.cores)

    fun getRandomWord(resources: Resources) = capitalizeFirstLetter(resources.getRawTextFileRandomLine(R.raw.palavras))

    fun getRandomAnimal(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.animais)

    fun getRandomBigWord(resources: Resources) = capitalizeFirstLetter(resources.getRawTextFileRandomLine(R.raw.grandes))

    fun getRandomEngWord(resources: Resources) = capitalizeFirstLetter(resources.getRawTextFileRandomLine(R.raw.words))

    private fun Resources.getRawTextFileRandomLine(@RawRes id: Int) =
        openRawResource(id).bufferedReader().use { it.readLines().random() }

    private fun capitalizeFirstLetter(word: String) = word.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}