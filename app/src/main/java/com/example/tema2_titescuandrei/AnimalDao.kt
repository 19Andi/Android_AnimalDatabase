package com.example.tema2_titescuandrei

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tema2_titescuandrei.models.Animal

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animal: Animal)

    @Update
    suspend fun update(animal: Animal)

    @Delete
    suspend fun delete(animal: Animal)

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals WHERE name = :name LIMIT 1")
    suspend fun getAnimalByName(name: String): Animal?
}