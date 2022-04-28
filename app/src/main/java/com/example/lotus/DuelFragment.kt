package com.example.lotus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentDuelBinding
import com.example.lotus.src.GameCards

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DuelFragment : Fragment() {

    private var _binding: FragmentDuelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val gameCards = GameCards()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDuelBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameNameView = view.findViewById<TextView>(R.id.card_name)

        binding.buyButton.setOnClickListener {
            val duelCard = gameCards.getRandomDuelCard()
            gameNameView.text = duelCard.name
        }

        binding.exitButton.setOnClickListener {
            findNavController().navigate(R.id.action_DuelFragment_to_CardsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}