package com.me.chrisbybits.friends_app.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.me.chrisbybits.friends_app.models.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>
    @Insert
    fun insertOne(person: Person)
    @Delete
    fun delete(person: Person)
}