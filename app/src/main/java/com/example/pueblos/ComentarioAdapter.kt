package com.example.pueblos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pueblos.R

// ComentarioAdapter ya no depende de la clase Comentario
class ComentarioAdapter(private val comentarios: MutableList<String>) : RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario, parent, false)
        return ComentarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
        // Aquí los comentarios son Strings, no objetos Comentario
        holder.bind(comentarios[position])
    }

    override fun getItemCount(): Int = comentarios.size

    inner class ComentarioViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val tvComentario: TextView = itemView.findViewById(R.id.tvComentario)
        private val rbCalificacion: RatingBar = itemView.findViewById(R.id.rbCalificacion)

        fun bind(comentario: String) {
            tvComentario.text = comentario
            // Aquí puedes establecer una calificación fija si no deseas usar una calificación
            rbCalificacion.rating = 3f // Por ejemplo, asignar una calificación de 3
        }
    }
}
