package com.example.lotus

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentCapitalsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CapitalsFragment : Fragment() {

    private var _binding: FragmentCapitalsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var countryValues: List<String>
    private val helpers = Helper()
    private var showingCapital = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCapitalsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryViewName = view.findViewById<TextView>(R.id.country_name)
        newCountry(countryViewName)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_CapitalsFragment_to_FirstFragment)
        }

        binding.cardsButton.setOnClickListener {
            findNavController().navigate(R.id.action_CapitalsFragment_to_GameFragment)
        }

        binding.answerButton.setOnClickListener {
            showingCapital = showingCapital.not()

            if (showingCapital) {
                countryViewName.setTextColor(Color.GREEN)
                countryViewName.text = countryValues[1]
            } else {
                countryViewName.setTextColor(Color.BLACK)
                countryViewName.text = countryValues[0]
            }
        }

        binding.nextButton.setOnClickListener {
            showingCapital = false
            newCountry(countryViewName)
            countryViewName.setTextColor(Color.BLACK)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newCountry(countryView: TextView) {
        countryValues = helpers.getRandomCountryList(resources).split(",")
        countryView.text = countryValues[0]
    }
}