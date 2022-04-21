package com.example.lotus

import android.content.res.Resources
import androidx.annotation.RawRes

class Helper {
    fun getRandomCountryList(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.paises)

    fun getRandomCountry(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.paises).split(',')[0]

    fun getRandomFruit(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.frutas)

    fun getRandomColor(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.cores)

    fun getRandomWord(resources: Resources) = resources.getRawTextFileRandomLine(R.raw.palavras)

    private fun Resources.getRawTextFileRandomLine(@RawRes id: Int) =
        openRawResource(id).bufferedReader().use { it.readLines().random() }
}