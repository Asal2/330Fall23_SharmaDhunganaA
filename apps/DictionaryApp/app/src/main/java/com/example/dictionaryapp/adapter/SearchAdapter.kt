// Import necessary packages and classes
package com.example.dictionaryapp.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dictionaryapp.databinding.SearchLayoutBinding

// Create a custom adapter class extending ListAdapter and specifying the data type (String) and ViewHolder type (WordViewHolder)
class SearchAdapter(val clickListener: WordListener) :
    ListAdapter<String, SearchAdapter.WordViewHolder>(WordsComparator()) {

    // Override the onCreateViewHolder method to inflate the item view layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        // Return a new instance of WordViewHolder with the inflated layout
        return WordViewHolder(
            SearchLayoutBinding.inflate(layoutInflater, parent, false)
        )
    }

    // Override the onBindViewHolder method to bind data to the ViewHolder
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        // Get the current String object at the given position
        val current = getItem(position)
        // Bind the current String to the ViewHolder using the bind method
        holder.bind(current, clickListener)
    }

    // Define the ViewHolder class for the RecyclerView
    class WordViewHolder(
        var binding: SearchLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // Bind method to set the data and click listener for the item
        fun bind(word: String?, clickListener: WordListener) {
            // Set the text of the search TextView with the current word
            binding.search.text = word

            // Set the click listener and execute any pending bindings
            binding.eventlistener = clickListener
            binding.executePendingBindings()

            // Set a click listener for the item view
            binding.root.setOnClickListener {
                clickListener.onClick(word!!)
            }
        }
    }

    // Define a custom DiffUtil.ItemCallback to efficiently update the list
    class WordsComparator : DiffUtil.ItemCallback<String>() {
        // Check if items are the same based on their references
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        // Check if the content of items is the same based on their values
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}

// Define a custom listener class to handle item click events
class WordListener(val clickListener: (word: String) -> Unit) {
    // Method to handle the click event and invoke the provided function
    fun onClick(word: String) = clickListener(word)
}
