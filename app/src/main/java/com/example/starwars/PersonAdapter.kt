package com.example.starwars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.model.data.Person

class PersonAdapter(private val onItemClick: (Person) -> Unit) :
    ListAdapter<Person, PersonAdapter.PersonViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_person_detail, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        person?.let {
            holder.bind(it, onItemClick)
        }
    }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(person: Person, onItemClick: (Person) -> Unit) {
            nameTextView.text = person.name
            itemView.setOnClickListener { onItemClick(person) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
    }
}
