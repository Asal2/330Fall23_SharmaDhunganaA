package com.example.dictionaryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.databinding.SearchLayoutBinding

class SearchAdapter(val clickListener: WordListener) :
    ListAdapter<String, SearchAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)


        return WordViewHolder(
            SearchLayoutBinding.inflate(layoutInflater,parent,false)
        )

    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }

    class WordViewHolder(
        var binding: SearchLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: String?, clickListener: WordListener) {

            binding.search.text = word

            binding.eventlistener = clickListener
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                clickListener.onClick(word!!)
            }

        }
    }

    class WordsComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}


class WordListener(val clickListener: (word: String) -> Unit) {
    fun onClick(word: String) = clickListener(word)
}