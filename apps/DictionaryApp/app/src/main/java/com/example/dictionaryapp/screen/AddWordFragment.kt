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

class AddWordFragment : Fragment() {
    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!

    private val sharedviewModel: DictionaryViewViewModel by activityViewModels()

    private val wordViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedviewModel
            addWordFragment = this@AddWordFragment
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedviewModel.word.observe(viewLifecycleOwner) { word ->
            binding.wordfound.text = word.id
            binding.worddefs.text = word.shortdefs
            binding.imageAdd.load("${sharedviewModel.getURL()}${word.imageFileName}.gif")
        }

        binding.addBtn.setOnClickListener{
            addWordToDatabase()
        }
    }

    fun addWordToDatabase() {

        wordViewModel.insertWord(sharedviewModel.getWord())
        findNavController().navigate(R.id.action_addWordFragment_to_HomeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}