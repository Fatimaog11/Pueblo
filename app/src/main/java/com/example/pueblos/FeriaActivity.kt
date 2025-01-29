package com.example.pueblos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class FeriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feria)

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        // Lista de imágenes en IntArray en lugar de List<Int>
        val images = listOf(
            R.drawable.feria1,
            R.drawable.feria2,
            R.drawable.feria3,
            R.drawable.feria4
        )

        // Configura el adaptador para el ViewPager2
        val adapter = ImageAdapter(images)
        viewPager2.adapter = adapter

        val btnEventos: ImageView = findViewById(R.id.btnEventos)
        val btnEventosText: TextView = findViewById(R.id.btnEventostxt)
        val btnPueblo: ImageView = findViewById(R.id.btnPueblo)
        val btnPueblotxt: TextView = findViewById(R.id.btnPueblotxt)
        val btnAtractivos: ImageView = findViewById(R.id.btnAtractivos)
        val btnAtractivostxt: TextView = findViewById(R.id.btnAtractivostxt)

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
