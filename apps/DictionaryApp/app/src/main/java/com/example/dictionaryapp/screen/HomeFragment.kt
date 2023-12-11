// Import necessary packages and classes
package com.example.dictionaryapp.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.DictionaryApp
import com.example.dictionaryapp.R
import com.example.dictionaryapp.adapter.HomeAdapter
import com.example.dictionaryapp.adapter.HomeListener
import com.example.dictionaryapp.databinding.FragmentHomeBinding
import com.example.dictionaryapp.viewmodel.DictionaryViewModel
import com.example.dictionaryapp.viewmodel.DictionaryViewModelFactory
import com.example.dictionaryapp.viewmodel.DictionaryViewViewModel

// Define the HomeFragment class, extending Fragment
class HomeFragment : Fragment() {

    // View binding variable for the fragment
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Shared ViewModel for communication between fragments
    private val sharedViewModel: DictionaryViewViewModel by activityViewModels()

    // ViewModel for word-related operations
    private val wordViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApp).repository)
    }

    // Override the onCreateView method to inflate the fragment's layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Set up data binding and bind ViewModel, lifecycle owner, and RecyclerView layout manager
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            wordsdic.layoutManager = LinearLayoutManager(requireContext())
        }
        return binding.root
    }

    // Override the onViewCreated method to set up UI components and observe data changes
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the fragment as the listener for data binding
        binding?.dictionaryHomeFragment = this

        // Create an adapter for the RecyclerView with a click listener
        val adapter = HomeAdapter(HomeListener { word ->
            // Set the selected word in the sharedViewModel
            sharedViewModel.setWord(word)
            // Log the selected word's image filename for debugging
            Log.d("DictionaryHome word obj", word.imageFileName.toString())
            // Navigate to the word definition fragment
            findNavController().navigate(R.id.action_dictionaryHomeFragment_to_wordDefinitionFragment)
        })

        // Set the adapter for the RecyclerView
        binding.wordsdic.adapter = adapter

        // Observe changes in the list of words and update the adapter
        wordViewModel.allWords.observe(viewLifecycleOwner) { words ->
            words.let { adapter.submitList(it) }
        }
    }

    // Method to navigate to the SearchFragment
    fun goToSearchWord() {
        findNavController().navigate(R.id.action_dictionaryHomeFragment_to_SearchFragment)
    }

    // Override the onDestroyView method to clean up the binding variable
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
