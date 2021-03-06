package com.example.lotus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentGameBinding
import com.example.lotus.src.GameCards
import com.example.lotus.src.SharedPreference

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val competitiveMode = "competitiveMode"
    private val helpers = Helper()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference = SharedPreference(requireContext())
        var competitiveMode = sharedPreference.getValueBoolean(competitiveMode, false)
        val gameNameView = view.findViewById<TextView>(R.id.card_name)
        val gameCards = (activity as MainActivity?)!!.getGameCards()

        if (competitiveMode) {
            binding.competitiveSwitch.toggle()
        }

        binding.competitiveSwitch.setOnCheckedChangeListener { _ , isChecked ->
            competitiveMode = isChecked
            sharedPreference.save(this.competitiveMode, competitiveMode)
        }

        binding.buyButton.setOnClickListener {
            val animSlideOut = AnimationUtils.loadAnimation(requireContext(),R.anim.slide_out);
            val animSlideIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in);
            binding.gameButton.visibility = View.INVISIBLE

            val gameCard = gameCards.getRandomGameCard(competitiveMode)
            gameNameView.startAnimation(animSlideOut);

            gameNameView.postDelayed({
                gameNameView.text = gameCard.name
                gameNameView.startAnimation(animSlideIn)

                binding.gameButton.visibility = if (gameCard.game != null) View.VISIBLE else View.INVISIBLE

                if (gameCard.game != null) {
                    binding.gameButton.setOnClickListener {
                        findNavController().navigate(gameCard.game)
                    }
                }
            }, helpers.animationTime.toLong())
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