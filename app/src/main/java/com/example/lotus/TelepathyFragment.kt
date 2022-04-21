package com.example.lotus

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentTelepathyBinding

class TelepathyFragment : Fragment() {

    private var _binding: FragmentTelepathyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val helper = Helper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTelepathyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val randomNumber = (0..2).random()

        val firstWord = view.findViewById<TextView>(R.id.first_word)
        val secondWord = view.findViewById<TextView>(R.id.second_word)

        firstWord.text = getRandomWord(randomNumber)
        secondWord.text = getRandomWord(randomNumber)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_telepathyFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRandomWord(id: Number): String {
        when (id) {
            0 -> return helper.getRandomCountry(resources)
            1 -> return helper.getRandomFruit(resources)
            2 -> return helper.getRandomColor(resources)
        }

        return ""
    }

    private fun readWordsFile() = resources.getRawTextFileRandomLine(R.raw.palavras)

    private fun Resources.getRawTextFileRandomLine(@RawRes id: Int) =
        openRawResource(id).bufferedReader().use { it.readLines().random() }
}