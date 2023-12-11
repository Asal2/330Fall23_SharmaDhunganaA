// Import necessary packages and classes
package com.example.dictionaryapp.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import android.os.Bundle
import com.example.dictionaryapp.DictionaryApp
import com.example.dictionaryapp.databinding.FragmentActivateDefinitionBinding
import com.example.dictionaryapp.viewmodel.DictionaryViewModel
import com.example.dictionaryapp.viewmodel.DictionaryViewModelFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionaryapp.viewmodel.DictionaryViewViewModel

// Define the ActivateDefinitionFragment class, extending Fragment
class ActivateDefinitionFragment : Fragment() {

    // View binding variable for the fragment
    private var _binding: FragmentActivateDefinitionBinding? = null
    private val binding get() = _binding!!

    // Shared ViewModel for communication between fragments
    private val viewModel: DictionaryViewViewModel by activityViewModels()

    // ViewModel for word-related operations
    private val wordViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApp).repository)
    }

    // Shared ViewModel for communication between fragments
    private val sharedViewModel: DictionaryViewViewModel by activityViewModels()

    // Override the onCreateView method to inflate the fragment's layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentActivateDefinitionBinding.inflate(inflater)
        // Set up data binding and bind ViewModel and lifecycle owner
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            wordDefinitionFragment = this@ActivateDefinitionFragment
        }
        return binding.root
    }

    // Override the onViewCreated method to set up UI components and observe data changes
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe changes in the word data in the sharedViewModel
        sharedViewModel.word.observe(viewLifecycleOwner) { word ->
            // Update UI elements with word data
            binding.word.text = word.id
            binding.wordDefni.text = word.shortdefs
            binding.imageAdd.load("${sharedViewModel.getURL()}${word.imageFileName}.gif")
        }

        // Set up a listener for the activation switch
        binding.activationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Update status to true when switch is checked
                wordViewModel.activateWord(sharedViewModel.getWord().id)
            } else {
                // Update status to false when switch is unchecked
                wordViewModel.deactivateWord(sharedViewModel.getWord().id)
            }
        }
    }

    // Override the onResume method to update UI elements when the fragment is resumed
    override fun onResume() {
        super.onResume()
        sharedViewModel.word.observe(viewLifecycleOwner) { word ->
            // Update activation switch based on the word's active status
            if (word.active) {
                binding.activationSwitch.text = "Active"
                binding.activationSwitch.isChecked = true
            } else {
                binding.activationSwitch.text = "Inactive"
                binding.activationSwitch.isChecked = false
            }
        }
    }

    // Override the onDestroyView method to clean up the binding variable
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
