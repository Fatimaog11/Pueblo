package com.example.pueblos

import com.google.firebase.Timestamp

data class Comentario(
    val texto: String = "",
    val calificacion: Float = 0f,
    val usuario: String = "",
    val fecha: Timestamp = Timestamp.now()
)