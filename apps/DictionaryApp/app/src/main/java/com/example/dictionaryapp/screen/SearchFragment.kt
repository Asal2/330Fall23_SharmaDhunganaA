// Import necessary packages and classes
package com.example.dictionaryapp.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionaryapp.adapter.SearchAdapter
import com.example.dictionaryapp.adapter.WordListener
import com.example.dictionaryapp.databinding.FragmentSearchBinding
import com.example.dictionaryapp.viewmodel.DictionaryViewViewModel
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.R

// Define the SearchFragment class, extending Fragment
class SearchFragment : Fragment() {

    // View binding variable for the fragment
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    // Shared ViewModel for communication between fragments
    private val viewModel: DictionaryViewViewModel by activityViewModels()

    // Override the onCreateView method to inflate the fragment's layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Set up data binding and bind ViewModel, lifecycle owner, and RecyclerView layout manager
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            wordSearchFragment = this@SearchFragment
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        return binding.root
    }

    // Override the onViewCreated method to set up UI components and observe data changes
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the fragment as the listener for data binding
        binding?.wordSearchFragment = this

        // Create an adapter for the RecyclerView with a click listener
        val adapter = SearchAdapter(WordListener { word ->
            // Handle word click event and navigate to the addWordFragment
            viewModel.onWordClicked(word)
            findNavController().navigate(R.id.action_wordSearchFragment_to_addWordFragment)
        })

        // Set the adapter for the RecyclerView
        binding.recyclerView.adapter = adapter

        // Set up a click listener for the search button
        binding.searchWordButton.setOnClickListener {
            // Call the method to show search results
            showResult()
        }

        // Observe changes in the word data in the ViewModel
        viewModel.word.observe(viewLifecycleOwner) { word ->
            // Handle word data changes if needed
        }

        // Observe changes in the list of words and update the adapter
        viewModel.words.observe(viewLifecycleOwner) { words ->
            words.let { adapter.submitList(it) }
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    // Override the onResume method to reset the word list in the ViewModel
    override fun onResume() {
        super.onResume()
        viewModel.resetWordList()
    }

    // Method to show search results based on the typed text
    fun showResult() {
        val typedText = binding.searchEditText.text.toString()
        viewModel.getWordResponse(typedText)
    }

    // Override the onDestroyView method to clean up the binding variable
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
