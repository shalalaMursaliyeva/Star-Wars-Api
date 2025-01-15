package com.example.starwars

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.FragmentPersonDetailBinding
import com.example.starwars.databinding.ItemPersonBinding
import com.example.starwars.model.data.Person

class PersonAdapter(private val onItemClick: (Person) -> Unit) :
    ListAdapter<Person, PersonAdapter.PersonViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        person?.let { holder.bind(it, onItemClick) }
    }

    class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person, onItemClick: (Person) -> Unit) {
            binding.nameTextView.text = person.name

            binding.eyeColorBox.setBackgroundColor(getColorFromEyeColor(person.eyeColor))
            binding.root.setOnClickListener { onItemClick(person) }
        }

        private fun getColorFromEyeColor(eyeColor: String?): Int {
            return when (eyeColor?.lowercase()) {
                "blue" -> Color.BLUE
                "brown" -> Color.parseColor("#A52A2A")
                "green" -> Color.GREEN
                "yellow" -> Color.YELLOW
                "red" -> Color.RED
                "pink" -> Color.MAGENTA
                "black" -> Color.BLACK
                "white" -> Color.WHITE
                else -> Color.LTGRAY
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
    }
}

