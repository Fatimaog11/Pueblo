package com.example.pueblos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {

    private lateinit var btnPueblo: ImageView
    private lateinit var btnAtractivos: ImageView
    private lateinit var btnEventos: ImageView
    private lateinit var btnHotel: ImageView
    private lateinit var btnRestaurantes: ImageView
    private lateinit var btnRutas: ImageView
    private lateinit var btnFeria: ImageView
    private lateinit var btnPaste: ImageView
    private lateinit var btnPlata: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        // Inicializa los botones circulares
        btnPueblo = findViewById(R.id.btnPueblo)
        btnAtractivos = findViewById(R.id.btnAtractivos)
        btnEventos = findViewById(R.id.btnEventos)
        btnHotel = findViewById(R.id.btnHotel)
        btnRestaurantes = findViewById(R.id.btnRestaurantes)
        btnRutas = findViewById(R.id.btnRutas)

        // Inicializa las imágenes de eventos
        btnFeria = findViewById(R.id.btnFeria)
        btnPaste = findViewById(R.id.btnPaste)
        btnPlata = findViewById(R.id.btnPlata)

        // Asigna listeners a cada botón circular
        btnPueblo.setOnClickListener {
            abrirActividad(PueblosActivity::class.java)
        }
        btnAtractivos.setOnClickListener {
            abrirActividad(AtractivosActivity::class.java)
        }
        btnEventos.setOnClickListener {
            abrirActividad(EventosActivity::class.java)
        }
        btnHotel.setOnClickListener {
            abrirActividad(HotelActivity::class.java)
        }
        btnRestaurantes.setOnClickListener {
            abrirActividad(RestaurantesActivity::class.java)
        }
        btnRutas.setOnClickListener {
            abrirActividad(RutasActivity::class.java)
        }

        // Asigna listeners a las imágenes de eventos
        btnFeria.setOnClickListener {
            abrirActividad(FeriaActivity::class.java)
        }
        btnPaste.setOnClickListener {
            abrirActividad(PasteActivity::class.java)
        }
        btnPlata.setOnClickListener {
            abrirActividad(PlataActivity::class.java)
        }
    }

    class PlataActivity {

    }

    class PasteActivity {

    }

    class FeriaActivity {

    }

    class RutasActivity {

    }

    class RestaurantesActivity {

    }

    class HotelActivity {

    }

    class EventosActivity {

    }

    class AtractivosActivity {

    }

    class PueblosActivity {

    }

    private fun abrirActividad(actividad: Class<*>) {
        val intent = Intent(this, actividad)
        startActivity(intent)
    }
}
