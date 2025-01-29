package com.example.pueblos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class InicioActivity extends AppCompatActivity {

    private ImageView btnPueblo;
    private ImageView btnAtractivos;
    private ImageView btnEventos;
    private ImageView btnHotel;
    private ImageView btnRestaurantes;
    private ImageView btnRutas;
    private ImageView btnFeria;
    private ImageView btnPaste;
    private ImageView btnPlata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Inicializa los botones circulares
        btnPueblo = findViewById(R.id.btnPueblo);
        btnAtractivos = findViewById(R.id.btnAtractivos);
        btnEventos = findViewById(R.id.btnEventos);
        btnHotel = findViewById(R.id.btnHotel);
        btnRestaurantes = findViewById(R.id.btnRestaurantes);
        btnRutas = findViewById(R.id.btnRutas);

        // Inicializa las imágenes de eventos
        btnFeria = findViewById(R.id.btnFeria);
        btnPaste = findViewById(R.id.btnPaste);
        btnPlata = findViewById(R.id.btnPlata);

        // Asigna listeners a cada botón circular
        btnEventos.setOnClickListener(v -> {
            abrirActividad(InicioActivity.class);
            btnEventos.setColorFilter(ContextCompat.getColor(this, R.color.rosa));
        });
        btnPueblo.setOnClickListener(v -> {
            abrirActividad(MainActivity.class);
            btnPueblo.setColorFilter(ContextCompat.getColor(this, R.color.rosa));
        });
        btnAtractivos.setOnClickListener(v -> {
            abrirActividad(AtractivosActivity.class);
            btnAtractivos.setColorFilter(ContextCompat.getColor(this, R.color.rosa));
        });


        // Asigna listeners a las imágenes de eventos
        btnFeria.setOnClickListener(v -> abrirActividad(FeriaActivity.class));
        btnPaste.setOnClickListener(v -> abrirActividad(PasteActivity.class));
        btnPlata.setOnClickListener(v -> abrirActividad(InfoActivity.class));
    }

    private void abrirActividad(Class<?> actividad) {
        Intent intent = new Intent(InicioActivity.this, actividad);
        startActivity(intent);
    }
}
