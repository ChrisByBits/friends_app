package com.me.chrisbybits.friends_app.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.me.chrisbybits.friends_app.R

class MainActivity : AppCompatActivity() {
    private lateinit var peopleBtn : Button
    private lateinit var favoritesBtn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peopleBtn = findViewById(R.id.btPeople)
        favoritesBtn = findViewById(R.id.btFavorites)


        peopleBtn.setOnClickListener {
            val intent = Intent(this, PeopleActivity::class.java)
            startActivity(intent)
        }

        favoritesBtn.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }
}