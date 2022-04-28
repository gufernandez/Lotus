package com.example.lotus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentCardsBinding
import kotlin.system.exitProcess

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gameButton.setOnClickListener {
            findNavController().navigate(R.id.action_CardsFragment_to_GameFragment)
        }

        binding.duelButton.setOnClickListener {
            findNavController().navigate(R.id.action_CardsFragment_to_DuelFragment)
        }

        binding.gamesScreenButton.setOnClickListener {
            findNavController().navigate(R.id.action_CardsFragment_to_FirstFragment)
        }

        binding.exitButton.setOnClickListener {
            exitProcess(-1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}