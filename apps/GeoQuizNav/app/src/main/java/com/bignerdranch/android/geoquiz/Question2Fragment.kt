package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestion2Binding

private const val TAG = "Question2Fragment"
private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class Question2Fragment : Fragment() {

    private var binding: FragmentQuestion2Binding? = null

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var isCheater = false

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isCheater =
                result.data?.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestion2Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState is set.")
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX_KEY, 0)
        }

        binding?.questionText?.setText(questionBank[currentIndex].testResId)

        binding?.nextButton?.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            binding?.questionText?.setText(questionBank[currentIndex].testResId)
        }

        binding?.trueButton?.setOnClickListener {
            checkAnswer(true)
        }

        binding?.falseButton?.setOnClickListener {
            checkAnswer(false)
        }

        binding?.cheatButton?.setOnClickListener {
            val answer = questionBank[currentIndex].answer
            val action=
                Question2FragmentDirections.actionQuestion2FragmentToCheatFragment(answer)
            this.findNavController().navigate(action)

        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val resId = when {
            isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        isCheater = false

        Toast.makeText(requireContext(), resId, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(CURRENT_INDEX_KEY, this.currentIndex)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
