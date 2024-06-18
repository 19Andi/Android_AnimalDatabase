package com.example.tema2_titescuandrei.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.tema2_titescuandrei.models.Animal

class AnimalAdapter(private val animalList: MutableList<Animal>, private val onDelete: (Animal) -> Unit) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalList[position]
        holder.tvName.text = animal.name
        holder.tvContinent.text = animal.continent

        holder.btnDelete.setOnClickListener {
            it.setBackgroundColor(it.context.resources.getColor(R.color.colorButtonPressed))

            onDelete(animal)

            Handler(Looper.getMainLooper()).postDelayed({
                it.setBackgroundColor(it.context.resources.getColor(R.color.colorButtonNormal))
            }, 500)
        }
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvContinent: TextView = itemView.findViewById(R.id.tvContinent)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    fun updateData(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }
}