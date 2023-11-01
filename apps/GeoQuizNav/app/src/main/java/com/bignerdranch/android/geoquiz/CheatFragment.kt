package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.bignerdranch.android.geoquiz.databinding.FragmentCheatBinding


private const val ARG_CORRECT_ANSWER="correct_answer"
private const val TAG="CheatFragment"
class CheatFragment : Fragment() {

    companion object {
        const val CHEAT_RESULT_KEY = "cheatResultKey"
        const val IS_CHEATER = "isCheater"
    }

    private var correctAnswer: Boolean = false
    private var binding: FragmentCheatBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "arguments in onCreate: ${arguments.toString()}")
        arguments?.let {
            correctAnswer = it.getBoolean(ARG_CORRECT_ANSWER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCheatBinding.inflate(inflater, container, false)
        return binding?.root ?: View(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.showAnswerButton?.setOnClickListener {
            val textVal = if (correctAnswer) "True" else "False"
            binding?.answerTextView?.text = textVal
            setFragmentResult(CHEAT_RESULT_KEY, bundleOf(IS_CHEATER to true))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }}