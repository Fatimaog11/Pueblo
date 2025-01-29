package com.example.pueblos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PueblosAdapter(listaPueblos: List<Any>) : RecyclerView.Adapter<PueblosAdapter.PuebloViewHolder>() {

    // ViewHolder interno que contiene las vistas del item
    class PuebloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombrePueblo: TextView = itemView.findViewById(R.id.tvNombrePueblo)
        val imagenPueblo: ImageView = itemView.findViewById(R.id.ivImagenPueblo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuebloViewHolder {
        // Inflamos el layout para el ítem del RecyclerView
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pueblo, parent, false)
        return PuebloViewHolder(view)
    }

    override fun onBindViewHolder(holder: PuebloViewHolder, position: Int) {
        // Configurar el contenido de cada ítem
        holder.nombrePueblo.text = "REAL DEL MONTE"
        holder.imagenPueblo.setImageResource(R.drawable.real)  // Imagen del pueblo
    }

    override fun getItemCount(): Int {
        return 1 // Mostrar solo un ítem por ahora
    }
}
