package com.me.chrisbybits.friends_app.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.chrisbybits.friends_app.R
import com.me.chrisbybits.friends_app.adapters.PersonAdapter
import com.me.chrisbybits.friends_app.db.AppDatabase
import com.me.chrisbybits.friends_app.models.Person
import com.me.chrisbybits.friends_app.prototypes.PersonPrototype

class FavoritesActivity : AppCompatActivity(), PersonPrototype.OnItemClickListener {
    private lateinit var rvFavorite: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        setSupportActionBar(findViewById(R.id.toolbar2))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        rvFavorite = findViewById(R.id.rvFavorite)
    }

    override fun onResume() {
        super.onResume()

        loadPeople { people ->
            rvFavorite.adapter = PersonAdapter(people, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoritesActivity)
        }
    }

    private fun loadPeople(onComplete: (List<Person>) -> Unit) {
        val dao = AppDatabase.getInstance(this).getDao()

        onComplete(dao.getAll())
    }

    override fun onItemClick(person: Person) {
        val dao = AppDatabase.getInstance(this).getDao()

        dao.delete(person)

        Toast.makeText(
            this,
            "Person ${person.firstName} deleted from favorites",
            Toast.LENGTH_SHORT
        ).show()

        loadPeople { people ->
            rvFavorite.adapter = PersonAdapter(people, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoritesActivity)
        }
    }
}