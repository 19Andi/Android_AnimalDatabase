package com.example.tema2_titescuandrei

import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.replace
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.myapplication.R
import com.example.tema2_titescuandrei.models.Animal

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etContinent: EditText
    private lateinit var btnAdd: Button
    private lateinit var animalListFragment: AnimalListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etContinent = findViewById(R.id.etContinent)
        btnAdd = findViewById(R.id.btnAdd)

        animalListFragment = AnimalListFragment()

        supportFragmentManager.commit {
            replace(R.id.fragment_container, animalListFragment)
        }

        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val continent = etContinent.text.toString().trim()
            etName.text.clear()
            etContinent.text.clear()

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(continent)) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                val animal = Animal(name = name.lowercase(), continent = continent)
                animalListFragment.addAnimal(animal)
            }
        }
    }
}