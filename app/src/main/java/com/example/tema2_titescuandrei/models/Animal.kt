package com.example.tema2_titescuandrei.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var continent: String
)