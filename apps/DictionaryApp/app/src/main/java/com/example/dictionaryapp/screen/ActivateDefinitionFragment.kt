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


class ActivateDefinitionFragment : Fragment() {

    private var _binding: FragmentActivateDefinitionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DictionaryViewViewModel by activityViewModels()

    private val wordViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApp).repository)
    }

    private val sharedViewModel: DictionaryViewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentActivateDefinitionBinding.inflate(inflater)
        //binding = fragmentBinding
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            wordDefinitionFragment = this@ActivateDefinitionFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.word.observe(viewLifecycleOwner) { word ->

            binding.word.text = word.id
            binding.wordDefni.text = word.shortdefs
            binding.imageAdd.load("${sharedViewModel.getURL()}${word.imageFileName}.gif")
        }

        binding.activationSwitch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                //update status to true
                wordViewModel.activateWord(sharedViewModel.getWord().id)
            } else {
                //update status to false
                wordViewModel.deactivateWord(sharedViewModel.getWord().id)

            }

        }

    }

    override fun onResume(){
        super.onResume()
        sharedViewModel.word.observe(viewLifecycleOwner) {word ->
            if(word.active){
                binding.activationSwitch.text = "Active"
                binding.activationSwitch.isChecked = true
            }
            else{
                binding.activationSwitch.text = "Inactive"
                binding.activationSwitch.isChecked = false
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}