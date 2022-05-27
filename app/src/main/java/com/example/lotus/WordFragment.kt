package com.example.lotus

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lotus.databinding.FragmentWordBinding
import com.example.lotus.src.SharedPreference

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WordFragment : Fragment() {

    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!
    private val helper: Helper = Helper()
    private val maxMilSecs: Long = 11000
    private val englishKeyName = "englishMode"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference = SharedPreference(requireContext())
        var englishEnabled = sharedPreference.getValueBoolean(englishKeyName, false)
        val wordView = view.findViewById<TextView>(R.id.word)
        val progressBar = binding.timeBar
        val secondsLabel = binding.secondsLabel

        val countDownTimer: CountDownTimer = object : CountDownTimer(maxMilSecs, 1) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = millisUntilFinished.toInt() - 1000
                secondsLabel.text = (millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                progressBar.progress = 0
                secondsLabel.text = "0"
            }
        }

        newWord(wordView, englishEnabled)
        initTimer(progressBar, secondsLabel, countDownTimer)

        if (englishEnabled) {
            binding.englishEnabled.toggle()
        }

        binding.englishEnabled.setOnCheckedChangeListener { _ , isChecked ->
            englishEnabled = isChecked
            sharedPreference.save(englishKeyName, englishEnabled)
        }

        binding.nextButton.setOnClickListener {
            newWord(wordView, englishEnabled)
            initTimer(progressBar, secondsLabel, countDownTimer)
        }

        binding.cardsButton.setOnClickListener {
            findNavController().navigate(R.id.action_WordFragment_to_GameFragment)
        }

        binding.returnButton.setOnClickListener {
            findNavController().navigate(R.id.action_WordFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newWord(wordView: TextView, englishEnabled: Boolean) {
        when (if (englishEnabled) (0..1).random() else 0) {
            0 -> wordView.text = helper.getRandomWord(resources)
            1 -> wordView.text = helper.getRandomEngWord(resources)
        }
    }

    private fun initTimer(progressBar: ProgressBar, secondsLabel: TextView, countDownTimer: CountDownTimer) {
        progressBar.max = (maxMilSecs - 1000).toInt()
        progressBar.min = 0
        progressBar.progress = (maxMilSecs - 1000).toInt()
        secondsLabel.text = (maxMilSecs/1000).toString()

        countDownTimer.cancel()
        countDownTimer.start()
    }
}