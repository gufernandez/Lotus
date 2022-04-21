package com.example.lotus

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentSecondBinding
import java.io.File
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.AsynchronousServerSocketChannel.open
import java.nio.channels.Pipe.open
import java.nio.file.Files
import java.nio.file.Paths

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var countryValues: List<String>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryViewName = view.findViewById<TextView>(R.id.country_name)
        newCountry(countryViewName)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.answerButton.setOnClickListener {
            countryViewName.text = countryValues[1]
        }

        binding.nextButton.setOnClickListener {
            newCountry(countryViewName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newCountry(countryView: TextView) {
        countryValues = readCountriesFile().split(",")
        countryView.text = countryValues[0]
    }

    private fun readCountriesFile() = resources.getRawTextFileRandomLine(R.raw.paises)

    private fun Resources.getRawTextFileRandomLine(@RawRes id: Int) =
        openRawResource(id).bufferedReader().use { it.readLines().random() }
}