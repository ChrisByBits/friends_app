package com.me.chrisbybits.friends_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.me.chrisbybits.friends_app.R
import com.me.chrisbybits.friends_app.models.Person
import com.me.chrisbybits.friends_app.prototypes.PersonPrototype

class PersonAdapter(private val people: List<Person>, private val clickListener: PersonPrototype.OnItemClickListener) : Adapter<PersonPrototype>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_user, parent, false)
        return PersonPrototype(view)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: PersonPrototype, position: Int) {
        holder.bind(people[position], clickListener)
    }
}