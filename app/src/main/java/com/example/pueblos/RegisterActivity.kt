package com.example.pueblos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var tvIniciarSesion: TextView

    // Firebase Authentication y Firestore
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Inicializar las vistas
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        tvIniciarSesion = findViewById(R.id.tvLogin)

        // Configurar el comportamiento de foco en los campos de texto
        setFocusListeners()

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val usuario = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (nombre.isNotEmpty() && correo.isNotEmpty() && usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                registrarUsuario(nombre, correo, usuario, contrasena)
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Enlace a la actividad de inicio de sesión
        tvIniciarSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setFocusListeners() {
        etNombre.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (etNombre.text.toString() == "Nombre") {
                    etNombre.setText("")
                }
            } else {
                if (etNombre.text.toString().isEmpty()) {
                    etNombre.setText("Nombre")
                }
            }
        }

        etCorreo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (etCorreo.text.toString() == "Correo") {
                    etCorreo.setText("")
                }
            } else {
                if (etCorreo.text.toString().isEmpty()) {
                    etCorreo.setText("Correo")
                }
            }
        }

        etUsuario.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (etUsuario.text.toString() == "Usuario") {
                    etUsuario.setText("")
                }
            } else {
                if (etUsuario.text.toString().isEmpty()) {
                    etUsuario.setText("Usuario")
                }
            }
        }

        etContrasena.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (etContrasena.text.toString() == "Contraseña") {
                    etContrasena.setText("")
                }
            } else {
                if (etContrasena.text.toString().isEmpty()) {
                    etContrasena.setText("Contraseña")
                }
            }
        }
    }

    private fun registrarUsuario(
        nombre: String,
        correo: String,
        usuario: String,
        contrasena: String
    ) {
        // Crear el usuario en Firebase Authentication
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Comprobar si el nombre ya existe
                    firestore.collection("Usuarios").document(nombre).get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                // Si ya existe un documento con ese nombre
                                Toast.makeText(
                                    this,
                                    "El nombre ya está registrado, elige otro",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // Crear el mapa de datos para Firestore
                                val userMap = hashMapOf(
                                    "nombre" to nombre,
                                    "correo" to correo,
                                    "usuario" to usuario,
                                    "contrasena" to contrasena
                                )

                                // Guardar los datos en Firestore usando el nombre como ID
                                firestore.collection("Usuarios").document(nombre)
                                    .set(userMap)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT)
                                            .show()

                                        // Ir a la pantalla de inicio de sesión o principal
                                        val intent = Intent(this, LoginActivity::class.java)
                                        intent.putExtra("nombreUsuario", nombre)
                                        startActivity(intent)
                                        finish()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(
                                            this,
                                            "Error al guardar los datos: ${e.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(
                                this,
                                "Error al verificar nombre: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    Toast.makeText(
                        this,
                        "Error en el registro: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}