package com.example.lotus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentFirstBinding
import kotlin.system.exitProcess

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.capitalsButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_CapitalsFragment)
        }

        binding.telepathyButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_TelepathyFragment)
        }

        binding.jogralButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_JogralFragment)
        }

        binding.musicButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_WordFragment)
        }

        binding.cardsButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_CardsFragment)
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