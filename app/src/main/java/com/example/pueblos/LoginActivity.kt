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

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegistrar: TextView

    // Firebase Authentication
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Inicialización de vistas
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegistrar = findViewById(R.id.tvRegistrar)

        // Configurar comportamiento del botón de iniciar sesión
        btnLogin.setOnClickListener {
            val correo = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (correo.isNotEmpty() && contrasena.isNotEmpty()) {
                iniciarSesion(correo, contrasena)
            } else {
                Toast.makeText(this, "Por favor, ingresa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar comportamiento del enlace para ir al registro
        tvRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Configurar el comportamiento de foco en los campos de texto
        setFocusListeners()
    }

    private fun setFocusListeners() {
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

    private fun iniciarSesion(correo: String, contrasena: String) {
        auth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
