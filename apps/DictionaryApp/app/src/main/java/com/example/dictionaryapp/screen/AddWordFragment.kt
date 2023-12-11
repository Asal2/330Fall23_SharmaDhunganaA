// Import necessary packages and classes
package com.example.dictionaryapp.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.dictionaryapp.DictionaryApp
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.FragmentAddWordBinding
import com.example.dictionaryapp.viewmodel.DictionaryViewModel
import com.example.dictionaryapp.viewmodel.DictionaryViewModelFactory
import com.example.dictionaryapp.viewmodel.DictionaryViewViewModel

// Define the AddWordFragment class, extending Fragment
class AddWordFragment : Fragment() {

    // View binding variable for the fragment
    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!

    // Shared ViewModel for communication between fragments
    private val sharedviewModel: DictionaryViewViewModel by activityViewModels()

    // ViewModel for word-related operations
    private val wordViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApp).repository)
    }

    // Override the onCreateView method to inflate the fragment's layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)

        // Set up data binding and bind ViewModel and lifecycle owner
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedviewModel
            addWordFragment = this@AddWordFragment
        }

        return binding.root
    }

    // Override the onViewCreated method to set up UI components and observe data changes
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe changes in the word data in the sharedViewModel
        sharedviewModel.word.observe(viewLifecycleOwner) { word ->
            // Update UI elements with word data
            binding.wordfound.text = word.id
            binding.worddefs.text = word.shortdefs
            binding.imageAdd.load("${sharedviewModel.getURL()}${word.imageFileName}.gif")
        }

        // Set up a click listener for the "Add" button
        binding.addBtn.setOnClickListener {
            // Call the method to add the word to the database
            addWordToDatabase()
        }
    }

    // Method to add the word to the database
    private fun addWordToDatabase() {
        // Insert the word using the wordViewModel
        wordViewModel.insertWord(sharedviewModel.getWord())
        // Navigate to the HomeFragment after adding the word
        findNavController().navigate(R.id.action_addWordFragment_to_HomeFragment)
    }

    // Override the onDestroyView method to clean up the binding variable
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
