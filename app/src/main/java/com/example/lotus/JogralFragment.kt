package com.example.lotus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentJogralBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class JogralFragment : Fragment() {

    private var _binding: FragmentJogralBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val helpers = Helper()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJogralBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wordView = view.findViewById<TextView>(R.id.jogralWord)
        newWord(wordView)

        binding.nextButtonJogral.setOnClickListener {
            newWord(wordView)
        }
        binding.returnButtonJogral.setOnClickListener {
            findNavController().navigate(R.id.action_JogralFragment_to_FirstFragment)
        }
        binding.cardsButton.setOnClickListener {
            findNavController().navigate(R.id.action_JogralFragment_to_GameFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newWord(wordView: TextView) {
        wordView.text = helpers.getRandomBigWord(resources)
    }
}