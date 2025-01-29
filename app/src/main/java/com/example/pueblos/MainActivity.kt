package com.example.pueblos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtén las referencias a los botones e íconos de texto
        val btnRealDelMonte: ImageView = findViewById(R.id.btnRealDelMonte)
        val btnEventos: ImageView = findViewById(R.id.btnEventos)
        val btnEventosText: TextView = findViewById(R.id.btnEventosText)
        val btnPueblo: ImageView = findViewById(R.id.btnPueblo)
        val btnPueblotxt: TextView = findViewById(R.id.btnPueblotxt)
        val btnAtractivos: ImageView = findViewById(R.id.btnAtractivos)
        val btnAtractivostxt: TextView = findViewById(R.id.btnAtractivostxt)

        // Configura el clic para lanzar InicioActivity y cambiar el color del texto
        btnRealDelMonte.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }

        // Configura el clic en el botón Eventos para abrir InicioActivity y cambiar color de texto
        btnEventos.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            btnEventosText.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }

        btnPueblo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            btnPueblotxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }
        btnAtractivos.setOnClickListener {
            val intent = Intent(this, AtractivosActivity::class.java)
            btnAtractivostxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }
    }
}
