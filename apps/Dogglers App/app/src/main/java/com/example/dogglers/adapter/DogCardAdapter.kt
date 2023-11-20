package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Initialize the data using the List found in data/DataSource
    private val dogs: List<Dog> = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Declare and initialize all of the list item UI components
        val dogImageView: ImageView = view.findViewById(R.id.dogImageView)
        val dogNameTextView: TextView = view.findViewById(R.id.dogNameTextView)
        val dogAgeTextView: TextView = view.findViewById(R.id.dogAgeTextView)
        val dogHobbiesTextView: TextView = view.findViewById(R.id.dogHobbiesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // Use a conditional to determine the layout type and set it accordingly.
        val inflater = LayoutInflater.from(parent.context)
        val view: View = when (layout) {
            Layout.GRID -> inflater.inflate(R.layout.grid_list_item, parent, false)
            Layout.VERTICAL, Layout.HORIZONTAL -> inflater.inflate(R.layout.vertical_horizontal_list_item, parent, false)
            else -> throw IllegalArgumentException("Invalid layout type")
        }

        // Update to reflect the inflated layout.
        return DogCardViewHolder(view)
    }

    override fun getItemCount(): Int = dogs.size // Return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // Get the data at the current position
        val dog = dogs[position]

        // Set the image resource for the current dog
        holder.dogImageView.setImageResource(dog.imageResourceId)

        // Set the text for the current dog's name
        holder.dogNameTextView.text = dog.name

        // Set the text for the current dog's age
        holder.dogAgeTextView.text = context?.getString(R.string.dog_age, dog.age)

        // Set the text for the current dog's hobbies by passing the hobbies to the
        // R.string.dog_hobbies string constant.
        holder.dogHobbiesTextView.text = context?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
