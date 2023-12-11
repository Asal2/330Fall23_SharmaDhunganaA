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

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: DictionaryViewViewModel by activityViewModels()
    private val wordViewModel: DictionaryViewModel by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)


        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            wordsdic.layoutManager = LinearLayoutManager(requireContext())
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.dictionaryHomeFragment = this
        val adapter = HomeAdapter(HomeListener { word ->
            sharedViewModel.setWord(word)
            Log.d("DictionaryHome word obj",word.imageFileName.toString())
            //navigate to definition
            findNavController().navigate(R.id.action_dictionaryHomeFragment_to_wordDefinitionFragment)
        })
        binding.wordsdic.adapter = adapter


        wordViewModel.allWords.observe(viewLifecycleOwner) { words ->
            Log.d("Tag", "Number of words: ${words.size}")
            words.let { adapter.submitList(it) }
        }
    }

    fun goToSearchWord(){
        findNavController().navigate(R.id.action_dictionaryHomeFragment_to_SearchFragment)

    }

    fun showActiveWords(){

    }
    fun showInactiveWords(){

    }

    fun showAllWords(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}