// MainActivity.kt
package com.example.pueblos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class AtractivosActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atractivos)

        // Encuentra el botón de Atractivos y configura el intent
        val btnEventos: ImageView = findViewById(R.id.btnEventos)
        val btnEventosText: TextView = findViewById(R.id.btnEventostxt)
        val btnPueblo: ImageView = findViewById(R.id.btnPueblo)
        val btnPueblotxt: TextView = findViewById(R.id.btnPueblotxt)
        val btnAtractivos: ImageView = findViewById(R.id.btnAtractivos)
        val btnAtractivostxt: TextView = findViewById(R.id.btnAtractivostxt)
        val btnparque: ImageView = findViewById(R.id.btnparque)
        val btnparquetxt: TextView = findViewById(R.id.btnparquetxt)
        val btnhiloche: ImageView = findViewById(R.id.btnhiloche)
        val btnhilochetxt: TextView = findViewById(R.id.btnhilochetxt)
        val btncascada: ImageView = findViewById(R.id.btncascada)
        val btncascadatxt: TextView = findViewById(R.id.btncascadatxt)
        val btnmina: ImageView = findViewById( R.id.btnMina)
        val btnminatxt: TextView = findViewById(R.id.btnMinatxt)
        val btnpanteon: ImageView = findViewById( R.id.btnpanteon)
        val btnpanteontxt: TextView = findViewById(R.id.btnpanteontxt)
        val btnSantuario: ImageView = findViewById( R.id.btnSantuario)
        val btnSantuariotxt: TextView = findViewById(R.id.btnSantuariotxt)


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
        btnparque.setOnClickListener {
            val intent = Intent(this, Parque_penasActivity::class.java)
            btnparquetxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }
        btnhiloche.setOnClickListener {
            val intent = Intent(this, HilocheActivity::class.java)
            btnhilochetxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }
        btncascada.setOnClickListener {
            val intent = Intent(this, CascadaBanActivity::class.java)
            btncascadatxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }
        btnmina.setOnClickListener {
            val intent = Intent(this, MinaActivity::class.java)
            btnminatxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }
        btnpanteon.setOnClickListener {
            val intent = Intent(this, PanteonActivity::class.java)
            btnpanteontxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }
        btnSantuario.setOnClickListener {
            val intent = Intent(this, ZelontlaActivity::class.java)
            btnSantuariotxt.setTextColor(ContextCompat.getColor(this, R.color.rosa))
            startActivity(intent)
        }

    }
}
