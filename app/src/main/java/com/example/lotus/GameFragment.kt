package com.example.lotus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentGameBinding
import com.example.lotus.src.Card
import com.example.lotus.src.GameCards

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val gameCards = GameCards()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var teamGame = binding.teamSwitch.isChecked
        val gameNameView = view.findViewById<TextView>(R.id.card_name)

        binding.teamSwitch.setOnCheckedChangeListener { _ , isChecked ->
            teamGame = isChecked
        }

        binding.buyButton.setOnClickListener {
            val gameCard = gameCards.getRandomGameCard(teamGame)
            gameNameView.text = gameCard.name

            binding.gameButton.visibility = if (gameCard.game != null) View.VISIBLE else View.INVISIBLE

            if (gameCard.game != null) {
                binding.gameButton.setOnClickListener {
                    findNavController().navigate(gameCard.game)
                }
            }
        }

        binding.exitButton.setOnClickListener {
            findNavController().navigate(R.id.action_GameFragment_to_CardsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}