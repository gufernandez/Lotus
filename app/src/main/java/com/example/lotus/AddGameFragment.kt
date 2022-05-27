package com.example.lotus

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentAddGameBinding
import com.example.lotus.src.GameCards
import java.lang.Exception

class AddGameFragment : Fragment() {

    private var _binding: FragmentAddGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val helpers = Helper()
    private val gameNameError = "Insira um nome válido para o novo jogo!"
    private val saveError = "Erro desconhecido ao salvar o novo jogo!"
    private val successMessage = "Jogo adicionado com sucesso!"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addGameButton.setOnClickListener {
            val gameName = binding.editGameName.text

            if (gameName == null || gameName.isEmpty()) {
                this.showToastMessage(view, gameNameError, false)
            }

            val isCompetitive = binding.isCompetitive.isChecked
            val isSolo = binding.isSolo.isChecked

            try {
                (activity as MainActivity?)!!.getGameCards().addGameCard(gameName.toString(), isCompetitive, isSolo)
                this.showToastMessage(view, successMessage, true)
                binding.editGameName.text = null

                if (isCompetitive) {
                    binding.isCompetitive.toggle()
                }

                if (isSolo) {
                    binding.isCompetitive.toggle()
                }
            } catch (e: Exception) {
                this.showToastMessage(view, saveError, false)
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_AddGameFragment_to_CardsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToastMessage(view: View, messageText: String, isSuccess: Boolean) {
        val toastMessageView = view.findViewById<TextView>(R.id.save_feedback)

        val animFadeOut = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_out)
        val animFadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)

        toastMessageView.text = messageText

        if (isSuccess) {
            toastMessageView.setTextColor(Color.GREEN)
        } else {
            toastMessageView.setTextColor(Color.RED)
        }

        toastMessageView.startAnimation(animFadeIn)
        toastMessageView.visibility = View.VISIBLE

        toastMessageView.postDelayed({
            toastMessageView.startAnimation(animFadeOut)
            toastMessageView.postDelayed({
                toastMessageView.visibility = View.INVISIBLE
            }, helpers.animationTime.toLong())
        }, helpers.animationTime.toLong())
    }
}