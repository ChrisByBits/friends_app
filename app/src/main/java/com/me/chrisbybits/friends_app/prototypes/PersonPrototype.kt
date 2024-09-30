package com.me.chrisbybits.friends_app.prototypes

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.me.chrisbybits.friends_app.models.Person
import com.squareup.picasso.Picasso
import com.me.chrisbybits.friends_app.R

class PersonPrototype(view: View) : RecyclerView.ViewHolder(view) {
    private val tvName: TextView = itemView.findViewById(R.id.tvName)
    private val tvCell: TextView = itemView.findViewById(R.id.tvCell)
    private val ivPhoto: ImageView = itemView.findViewById(R.id.ivPerson)
    private val like: ImageButton = itemView.findViewById(R.id.btLike)

    fun bind(person: Person, clickListener: OnItemClickListener) {
        tvName.text = person.firstName + " " + person.lastName
        tvCell.text = person.cell

        Picasso.get()
            .load(person.picture)
            .into(ivPhoto)
        like.setOnClickListener {
            clickListener.onItemClick(person)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(person: Person)
    }
}

