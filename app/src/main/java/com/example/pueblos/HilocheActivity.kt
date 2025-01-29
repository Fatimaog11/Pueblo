package com.example.pueblos

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HilocheActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiloche)

        // Inicializar Firebase
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        // Configurar ViewPager2
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        val images = listOf(
            R.drawable.hilo1,
            R.drawable.hilo2,
            R.drawable.hilo3,
            R.drawable.hilo4
        )
        viewPager2.adapter = ImageAdapter(images)

        // Botones de navegación
        val btnEventos: ImageView = findViewById(R.id.btnEventos)
        val btnEventosText: TextView = findViewById(R.id.btnEventostxt)
        val btnPueblo: ImageView = findViewById(R.id.btnPueblo)
        val btnPueblotxt: TextView = findViewById(R.id.btnPueblotxt)
        val btnAtractivos: ImageView = findViewById(R.id.btnAtractivos)
        val btnAtractivostxt: TextView = findViewById(R.id.btnAtractivostxt)

        btnEventos.setOnClickListener {
            btnEventosText.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(Intent(this, InicioActivity::class.java))
        }

        btnPueblo.setOnClickListener {
            btnPueblotxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnAtractivos.setOnClickListener {
            btnAtractivostxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(Intent(this, AtractivosActivity::class.java))
        }

        // Enviar comentario
        val editTextComentario: EditText = findViewById(R.id.etComentario)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val btnEnviarComentario: Button = findViewById(R.id.btnEnviarComentario)

        btnEnviarComentario.setOnClickListener {
            val comentarioTexto = editTextComentario.text.toString()
            val calificacion = ratingBar.rating

            if (comentarioTexto.isNotEmpty() && calificacion > 0) {
                guardarComentario(comentarioTexto, calificacion)
            } else {
                Toast.makeText(this, "Por favor, escribe un comentario y califica", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarComentario(
        comentarioTexto: String,
        calificacion: Float
    ) {
        val currentUser = auth.currentUser
        val uid = currentUser?.uid

        if (uid != null) {
            firestore.collection("Usuarios")
                .whereEqualTo("correo", currentUser.email)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        val document = documents.documents[0]
                        val nombreUsuario = document.getString("nombre") ?: "UsuarioAnónimo"

                        val datosComentario = mapOf(
                            "usuario" to nombreUsuario,
                            "texto" to comentarioTexto,
                            "calificacion" to calificacion,
                            "fecha" to Timestamp.now()
                        )

                        firestore.collection("comentarios")
                            .add(datosComentario)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Comentario enviado", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Error al enviar comentario", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al obtener el nombre del usuario", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
        }
    }
}
