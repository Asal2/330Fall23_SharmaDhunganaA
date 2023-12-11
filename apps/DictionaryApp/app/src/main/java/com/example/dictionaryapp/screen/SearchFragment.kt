package com.example.dictionaryapp.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.adapter.SearchAdapter
import com.example.dictionaryapp.adapter.WordListener
import com.example.dictionaryapp.databinding.FragmentSearchBinding
import com.example.dictionaryapp.viewmodel.DictionaryViewViewModel


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DictionaryViewViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.d("WordSearch","before inflate")
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        Log.d("WordSearch","after inflate")
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            wordSearchFragment = this@SearchFragment

            recyclerView.layoutManager = LinearLayoutManager(requireContext())

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Tag", "HERE")
        binding?.wordSearchFragment = this
        val adapter = SearchAdapter(WordListener { word ->
            viewModel.onWordClicked(word)
            // Navigate to the next destination to add a word
            findNavController().navigate(R.id.action_wordSearchFragment_to_addWordFragment)
            Log.d("WordSearch", "after findNav")
        })
        binding.recyclerView.adapter = adapter
        binding.searchWordButton.setOnClickListener{
            showResult()
        }
        viewModel.word.observe(viewLifecycleOwner) { word ->
        }

        viewModel.words.observe(viewLifecycleOwner){ words ->
            words.let { adapter.submitList(it) }
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.resetWordList()
    }
    fun showResult() {
        val typedText = binding.searchEditText.text.toString()
        viewModel.getWordResponse(typedText)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
