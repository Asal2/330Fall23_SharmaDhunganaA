package com.example.dictionaryapp.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.database.Word
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.dictionaryapp.databinding.HomeLayoutBinding


class HomeAdapter(val clickListener: HomeListener) :
    ListAdapter<Word, HomeAdapter.DictionaryViewHolder>(WordsComparator()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DictionaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return DictionaryViewHolder(
            HomeLayoutBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }


    class DictionaryViewHolder(var binding: HomeLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            word: Word,
            clickListener: HomeListener
        )  {
            binding.word = word
            binding.eventlistener = clickListener
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                clickListener.onClick(word)
            }

        }

    }


    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }
    }


}

class HomeListener(val clickListener: (word: Word) -> Unit) {
    fun onClick(word: Word) = clickListener(word)
}