package com.example.tema2_titescuandrei

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.tema2_titescuandrei.adapters.AnimalAdapter
import com.example.tema2_titescuandrei.models.Animal
import kotlinx.coroutines.launch

class AnimalListFragment : Fragment() {

    private lateinit var rvAnimals: RecyclerView
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animalDatabase: AnimalDatabase
    private lateinit var animalDao: AnimalDao

    private val validContinents = listOf("africa", "asia", "europe", "north america", "south america", "australia", "antarctica")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAnimals = view.findViewById(R.id.rvAnimals)

        animalAdapter = AnimalAdapter(mutableListOf()) { animal ->
            deleteAnimal(animal)
        }
        rvAnimals.layoutManager = LinearLayoutManager(context)
        rvAnimals.adapter = animalAdapter

        animalDatabase = AnimalDatabase.getDatabase(requireContext())
        animalDao = animalDatabase.animalDao()

        animalDao.getAllAnimals().observe(viewLifecycleOwner, Observer { animals ->
            animals?.let {
                animalAdapter.updateData(it)
            }
        })
    }

    fun addAnimal(animal: Animal) {
        lifecycleScope.launch {
            if (animal.continent.lowercase() !in validContinents) {
                Toast.makeText(context, "Invalid continent", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val duplicate = animalDao.getAnimalByName(animal.name.lowercase())
            if (duplicate != null) {
                val updateValue = duplicate.copy(continent=animal.continent)
                Toast.makeText(context, "1 Animal updated", Toast.LENGTH_SHORT).show()
                updateAnimal(updateValue)
            } else {
                animalDao.insert(animal)
            }
        }
    }

    fun updateAnimal(animal: Animal) {
        lifecycleScope.launch {
            animalDao.update(animal)
        }
    }

    fun deleteAnimal(animal: Animal) {
        lifecycleScope.launch {
            animalDao.delete(animal)
        }
    }
}
