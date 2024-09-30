package com.me.chrisbybits.friends_app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.chrisbybits.friends_app.R
import com.me.chrisbybits.friends_app.adapters.PersonAdapter
import com.me.chrisbybits.friends_app.communication.ApiResponse
import com.me.chrisbybits.friends_app.db.AppDatabase
import com.me.chrisbybits.friends_app.factories.UserServiceFactory
import com.me.chrisbybits.friends_app.models.Person
import com.me.chrisbybits.friends_app.prototypes.PersonPrototype
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleActivity : AppCompatActivity(), PersonPrototype.OnItemClickListener {

    private lateinit var btnLoad : Button
    private lateinit var etResults: EditText
    private lateinit var rvPeople: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        setSupportActionBar(findViewById(R.id.toolbar))
        btnLoad = findViewById(R.id.btLoadPeople)
        etResults = findViewById(R.id.etPeopleCount)
        rvPeople = findViewById(R.id.rvPeople)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        btnLoad.setOnClickListener {
            val results = etResults.text.toString().toInt()
            loadPeople(results) { people ->
                rvPeople.adapter = PersonAdapter(people, this)
                rvPeople.layoutManager = LinearLayoutManager(this@PeopleActivity)
            }
        }
    }

    private fun loadPeople(results : Int, onComplete: (List<Person>) -> Unit) {
        val userService= UserServiceFactory().createRandomServiceInstance();
        val request = userService.getUsers(results)

        request.enqueue(object: Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val userApiUsers : ApiResponse = response.body()!!
                    val personList = mutableListOf<Person>()

                    userApiUsers.results?.forEach {
                        personList.add(it.toPerson())
                    }

                    onComplete(personList)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    override fun onItemClick(person : Person) {
        val dao = AppDatabase.getInstance(this).getDao()
        dao.insertOne(person)

        Toast.makeText(this, "Person ${person.firstName} added to favorites", Toast.LENGTH_SHORT).show()
    }
}