// Import necessary packages and classes
package com.example.dictionaryapp.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.database.Word
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.dictionaryapp.databinding.HomeLayoutBinding

// Create a custom adapter class extending ListAdapter and specifying the data type (Word) and ViewHolder type (DictionaryViewHolder)
class HomeAdapter(val clickListener: HomeListener) :
    ListAdapter<Word, HomeAdapter.DictionaryViewHolder>(WordsComparator()){

    // Override the onCreateViewHolder method to inflate the item view layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DictionaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // Return a new instance of DictionaryViewHolder with the inflated layout
        return DictionaryViewHolder(
            HomeLayoutBinding.inflate(layoutInflater, parent, false)
        )
    }

    // Override the onBindViewHolder method to bind data to the ViewHolder
    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        // Get the current Word object at the given position
        val current = getItem(position)
        // Bind the current Word to the ViewHolder using the bind method
        holder.bind(current, clickListener)
    }

    // Define the ViewHolder class for the RecyclerView
    class DictionaryViewHolder(var binding: HomeLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        // Bind method to set the data and click listener for the item
        fun bind(
            word: Word,
            clickListener: HomeListener
        )  {
            binding.word = word
            binding.eventlistener = clickListener
            binding.executePendingBindings()
            // Set a click listener for the item view
            binding.root.setOnClickListener {
                clickListener.onClick(word)
            }
        }
    }

    // Define a custom DiffUtil.ItemCallback to efficiently update the list
    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        // Check if items are the same based on their references
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        // Check if the content of items is the same based on their IDs
        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

// Define a custom listener class to handle item click events
class HomeListener(val clickListener: (word: Word) -> Unit) {
    // Method to handle the click event and invoke the provided function
    fun onClick(word: Word) = clickListener(word)
}
